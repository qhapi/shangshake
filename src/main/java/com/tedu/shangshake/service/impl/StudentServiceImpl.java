package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.shangshake.mapper.*;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SpecialtyMapper specialtyMapper;

    @Override
    public Integer login(StudentLoginDTO studentLoginDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno", studentLoginDTO.getSno());
        queryWrapper.eq("password", studentLoginDTO.getPassword());

        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        if(studentDAO != null)
            return studentDAO.getSno();
        else
            return -1;
    }

    @Override
    public Integer register(StudentRegisterDTO studentRegisterDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno",studentRegisterDTO.getSno());

        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        if(studentDAO.getPassword() == null){
            studentDAO.setPhonenumber(studentRegisterDTO.getPhoneNumber());
            studentDAO.setPassword(studentRegisterDTO.getPassword());
            studentDAO.setUsername(studentRegisterDTO.getUserName());

            int update = studentMapper.update(studentDAO, queryWrapper);
            if(update != 0)
                return studentDAO.getSno();
            else
                throw new RuntimeException("注册出错");
        }
        else
            return -1;

    }

    @Override
    public StudentVO getStudentInfo(Integer sno) {
        QueryWrapper studentQuery = new QueryWrapper();
        studentQuery.eq("sno",sno);
        StudentDAO studentDAO = studentMapper.selectOne(studentQuery);

        if(studentDAO == null)
            return null;
        QueryWrapper specialtyQuery = new QueryWrapper();
        specialtyQuery.eq("spno",studentDAO.getSpno());
        SpecialtyDAO specialtyDAO = specialtyMapper.selectOne(specialtyQuery);

        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(studentDAO,studentVO);
        studentVO.setSpname(specialtyDAO.getSpname());

        return studentVO;
    }

    @Override
    public Boolean changePassword(StudentChangePasswordDTO studentChangePasswordDTO) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno",studentChangePasswordDTO.getSno());
        queryWrapper.eq("password",studentChangePasswordDTO.getOldPassword());

        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        if(studentDAO != null) {
            studentDAO.setPassword(studentChangePasswordDTO.getNewPassword());
            int update = studentMapper.update(studentDAO, queryWrapper);
            return update != 0;
        }
        else return false;
    }

    @Override
    public Boolean updateStudentInfo(StudentUpdateDTO studentUpdateDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno",studentUpdateDTO.getSno());
        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        if(studentDAO != null){
            studentDAO.setUsername(studentUpdateDTO.getUsername());
            studentDAO.setPhonenumber(studentUpdateDTO.getPhonenumber());
            studentDAO.setSpicture(studentUpdateDTO.getSpicture());

            int update = studentMapper.update(studentDAO, queryWrapper);
            return update != 0;
        }
        else return false;

    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    StudentCourseTeacherCurrentMapper currentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public List<SelfCourseVO> getSelfCourseTable(Integer sno) {
        String value = stringRedisTemplate.opsForValue().get(String.valueOf(sno));
        if(ObjectUtils.isEmpty(value)){
        //获取studentDAO
        QueryWrapper studentQuery = new QueryWrapper();
        studentQuery.eq("sno",sno);
        StudentDAO studentDAO = studentMapper.selectOne(studentQuery);

        //获取cno和tno
        QueryWrapper ctQuery = new QueryWrapper();
        ctQuery.eq("sno",sno);
        List<StudentCourseTeacherCurrentDAO> currentDAOS = currentMapper.selectList(ctQuery);

            SelfCourseList selfCourseList = new SelfCourseList();
        for(int i = 0 ; i < currentDAOS.size() ; i++)
        {
            //获取cname
            QueryWrapper courseQuery = new QueryWrapper();
            courseQuery.eq("cno",currentDAOS.get(i).getCno());
            CourseDAO courseDAO = courseMapper.selectOne(courseQuery);
            String cname = courseDAO.getCname();

            //获取week,section,startweek,endweek,teachplace
            QueryWrapper newctQuery = new QueryWrapper();
            newctQuery.eq("tno", currentDAOS.get(i).getTno());
            newctQuery.eq("cno", currentDAOS.get(i).getCno());
            CourseTeacherDAO courseTeacherDAO = courseTeacherMapper.selectOne(newctQuery);
            Integer week = courseTeacherDAO.getWeek();
            Integer section = courseTeacherDAO.getSection();
            Integer beginweek = courseTeacherDAO.getCbeginweek();
            Integer endweek =courseTeacherDAO.getCendweek();
            String teachplace = courseTeacherDAO.getTeachplace();
            QueryWrapper teacherQuery = new QueryWrapper();
            teacherQuery.eq("tno",currentDAOS.get(i).getTno());
            TeacherDAO teacherDAO = teacherMapper.selectOne(teacherQuery);

            String tname = teacherDAO.getTname();

            SelfCourseVO selfCourseVO = new SelfCourseVO(currentDAOS.get(i).getCno(), cname, week, section, beginweek, endweek, teachplace, tname);

            selfCourseList.list.add(selfCourseVO);
        }


            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(selfCourseList);
                stringRedisTemplate.opsForValue().set(String.valueOf(sno),jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return selfCourseList.list;
        }
        else{
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                SelfCourseList selfCourseList = objectMapper.readValue(value, SelfCourseList.class);
                return selfCourseList.list;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
