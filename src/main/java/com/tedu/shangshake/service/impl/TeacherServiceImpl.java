package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.shangshake.mapper.AppraiseMapper;
import com.tedu.shangshake.mapper.StudentMapper;
import com.tedu.shangshake.mapper.StudentTeacherAppraiseMapper;
import com.tedu.shangshake.mapper.TeacherMapper;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentTeacherAppraiseMapper studentTeacherAppraiseMapper;
    @Autowired
    AppraiseMapper appraiseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public TeacherDetailVO getTeacherDetail(Integer teacherId){
        TeacherDAO teacherDAO = new TeacherDAO();
        String getTnoDetail = stringRedisTemplate.opsForValue().get("tno"+String.valueOf(teacherId));
        if(ObjectUtils.isEmpty(getTnoDetail)){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("tno",teacherId);
            teacherDAO = teacherMapper.selectOne(queryWrapper);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(teacherDAO);
                stringRedisTemplate.opsForValue().set("tno"+String.valueOf(teacherId),jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                teacherDAO = objectMapper.readValue(getTnoDetail, TeacherDAO.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        TeacherDetailVO teacherDetailVO = new TeacherDetailVO();
        BeanUtils.copyProperties(teacherDAO,teacherDetailVO);
        return teacherDetailVO;
    }

    @Override
    public boolean addTeacherAppraise(StudentTeacherAppraiseInsertDTO studentTeacherAppraiseInsertDTO) {
        //???????????????Appraise???
        AppraiseDAO appraiseDAO = new AppraiseDAO();
        BeanUtils.copyProperties(studentTeacherAppraiseInsertDTO,appraiseDAO);
        appraiseDAO.setAcontent(studentTeacherAppraiseInsertDTO.getContent());
        int insertRow = appraiseMapper.insert(appraiseDAO);

        QueryWrapper maxid = new QueryWrapper();
        maxid.orderByDesc("ano");
        List<AppraiseDAO> maxids= appraiseMapper.selectList(maxid);

        if(insertRow >= 1){
            //???????????????sta???studentTeacherApprase???
            StudentTeacherAppraiseDAO studentTeacherAppraiseDAO = new StudentTeacherAppraiseDAO();
            BeanUtils.copyProperties(studentTeacherAppraiseInsertDTO,studentTeacherAppraiseDAO);
            studentTeacherAppraiseDAO.setAno(maxids.get(0).getAno());
            int insert = studentTeacherAppraiseMapper.insert(studentTeacherAppraiseDAO);
            if(insert>=1){
                //?????????????????????????????????
                //????????????????????????ano??????
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("tno",studentTeacherAppraiseInsertDTO.getTno());
                List<StudentTeacherAppraiseDAO> studentTeacherAppraiseDAOS = studentTeacherAppraiseMapper.selectList(queryWrapper);
                int cnt = 0;
                float sum = 0;
                for(StudentTeacherAppraiseDAO sca:studentTeacherAppraiseDAOS){
                    //??????ano??????astar
                    QueryWrapper getStarQW = new QueryWrapper();
                    getStarQW.eq("ano",sca.getAno());
                    AppraiseDAO starAppraiseDAO = appraiseMapper.selectOne(getStarQW);
                    sum += starAppraiseDAO.getAstar();
                    cnt++;
                }
                QueryWrapper updateQW = new QueryWrapper();
                updateQW.eq("tno",studentTeacherAppraiseInsertDTO.getTno());
                TeacherDAO teacherDAO = new TeacherDAO();
                teacherDAO.setAveragestar(new Float(sum/cnt));
                teacherMapper.update(teacherDAO,updateQW);
                return true;
            }else {
                //???????????????????????????
                QueryWrapper deleteAppraise = new QueryWrapper();
                deleteAppraise.eq("ano",maxids.get(0).getAno());
                appraiseMapper.delete(deleteAppraise);
                return false;
            }
        }else
            return false;
    }

    @Override
    public List<TeacherAppraiseVO> getTeacherAppraise(Integer teacherId) {
        //????????????????????????ano??????
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tno",teacherId);
        List<StudentTeacherAppraiseDAO> studentTeacherAppraiseDAOS = studentTeacherAppraiseMapper.selectList(queryWrapper);
        //????????????????????????
        ArrayList<TeacherAppraiseVO> teacherAppraiseVOArrayList = new ArrayList<>();
        for(StudentTeacherAppraiseDAO studentTeacherAppraiseDAO:studentTeacherAppraiseDAOS){
            QueryWrapper appraiseDetailQW = new QueryWrapper();
            appraiseDetailQW.eq("ano",studentTeacherAppraiseDAO.getAno());
            AppraiseDAO appraiseDAO = appraiseMapper.selectOne(appraiseDetailQW);

            TeacherAppraiseVO teacherAppraiseVO = new TeacherAppraiseVO();
            BeanUtils.copyProperties(appraiseDAO,teacherAppraiseVO);
            //??????tno??????????????????
            QueryWrapper getUsernameQW = new QueryWrapper();
            getUsernameQW.eq("sno",studentTeacherAppraiseDAO.getSno());
            StudentDAO studentDAO = studentMapper.selectOne(getUsernameQW);
            teacherAppraiseVO.setUsername(studentDAO.getUsername());

            teacherAppraiseVOArrayList.add(teacherAppraiseVO);
        }
        return teacherAppraiseVOArrayList;
    }
}
