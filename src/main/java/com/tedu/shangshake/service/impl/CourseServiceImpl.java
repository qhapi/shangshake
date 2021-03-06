package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.shangshake.mapper.*;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AppraiseMapper appraiseMapper;
    @Autowired
    KindMapper kindMapper;
    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Autowired
    StudentCourseAppraiseMapper studentCourseAppraiseMapper;
    @Autowired
    StudentCourseTeacherMapper studentCourseTeacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<CourseVO> getCourse(Integer page, Integer num) {
        //stringRedisTemplate.delete("1");
        List<String> valueList = stringRedisTemplate.opsForList().range( "getCourse", 0, -1);
        List<CourseDAO> courseDAOS = new ArrayList<>();
        if(ObjectUtils.isEmpty(valueList)){
            courseDAOS = courseMapper.selectList(null);
            ObjectMapper objectMapper = new ObjectMapper();
            for (CourseDAO courseDAO:courseDAOS){
                try {
                    String jsonString = objectMapper.writeValueAsString(courseDAO);
                    stringRedisTemplate.opsForList().rightPush("getCourse",jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();

            for(String value:valueList){
                try {
                    CourseDAO courseDAO = objectMapper.readValue(value, CourseDAO.class);
                    courseDAOS.add(courseDAO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }

        ArrayList<CourseVO> courseVOS = new ArrayList<>();
        for (CourseDAO courseDAO:courseDAOS){
            //???????????????????????????
            KindDAO kindDAO = new KindDAO();
            String getKnoName = stringRedisTemplate.opsForValue().get("kno"+String.valueOf(courseDAO.getKno()));
            if(ObjectUtils.isEmpty(getKnoName)){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("kno",courseDAO.getKno());
                kindDAO = kindMapper.selectOne(queryWrapper);

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(kindDAO);
                    stringRedisTemplate.opsForValue().set("kno"+String.valueOf(courseDAO.getKno()),jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    kindDAO = objectMapper.readValue(getKnoName, KindDAO.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }


            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(courseDAO,courseVO);
            courseVO.setkName(kindDAO.getKname());
            courseVOS.add(courseVO);
        }
        ArrayList<CourseVO> list = new ArrayList<>();
        for(int i = (page-1)*num; i < courseVOS.size(); i++){
            list.add(courseVOS.get(i));
            if(list.size() == num) break;
        }
        return list;
    }

    @Override
    public List<CourseDetailVO> getCourseDetail(Integer courseId) {
        //???????????????????????????
        CourseDAO courseDAO = new CourseDAO();
        String getCnoDetail = stringRedisTemplate.opsForValue().get("cno"+String.valueOf(courseId));
        if(ObjectUtils.isEmpty(getCnoDetail)){
            QueryWrapper courseInfoQW = new QueryWrapper();
            courseInfoQW.eq("cno",courseId);
            courseDAO = courseMapper.selectOne(courseInfoQW);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(courseDAO);
                stringRedisTemplate.opsForValue().set("cno"+String.valueOf(courseId),jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                courseDAO = objectMapper.readValue(getCnoDetail, CourseDAO.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        //??????courseId????????????????????????????????????list
        List<String> valueList = stringRedisTemplate.opsForList().range( "getCourseTeacher", 0, -1);
        List<CourseTeacherDAO> courseTeacherDAOList = new ArrayList<>();
        if(ObjectUtils.isEmpty(valueList)){
            QueryWrapper tcListQW = new QueryWrapper();
            tcListQW.eq("cno",courseDAO.getCno());
            courseTeacherDAOList=courseTeacherMapper.selectList(tcListQW);
            ObjectMapper objectMapper = new ObjectMapper();
            for (CourseTeacherDAO courseTeacherDAO:courseTeacherDAOList){
                try {
                    String jsonString = objectMapper.writeValueAsString(courseTeacherDAO);
                    stringRedisTemplate.opsForList().rightPush("getCourseTeacher",jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();

            for(String value:valueList){
                try {
                    CourseTeacherDAO courseTeacherDAO = objectMapper.readValue(value, CourseTeacherDAO.class);
                    courseTeacherDAOList.add(courseTeacherDAO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }


        ArrayList<CourseDetailVO> courseDetailVOList = new ArrayList<>();

        for(CourseTeacherDAO courseTeacherDAO:courseTeacherDAOList){
            CourseDetailVO courseDetailVO = new CourseDetailVO();
            BeanUtils.copyProperties(courseDAO,courseDetailVO);
            BeanUtils.copyProperties(courseTeacherDAO,courseDetailVO);
            //??????tno??????tname
            TeacherDAO teacherDAO = new TeacherDAO();
            String getTnoDetail = stringRedisTemplate.opsForValue().get("tno"+String.valueOf(courseTeacherDAO.getTno()));
            if(ObjectUtils.isEmpty(getTnoDetail)){
                QueryWrapper getTnameQW = new QueryWrapper();
                getTnameQW.eq("tno",courseTeacherDAO.getTno());
                teacherDAO = teacherMapper.selectOne(getTnameQW);

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(teacherDAO);
                    stringRedisTemplate.opsForValue().set("tno"+String.valueOf(courseTeacherDAO.getTno()),jsonString);
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


            courseDetailVO.setTname(teacherDAO.getTname());

            courseDetailVOList.add(courseDetailVO);
        }

        return courseDetailVOList;
    }

    @Override
    public boolean addCourseAppraise(StudentCourseAppraiseInsertDTO studentCourseAppraiseInsertDTO) {
        //??????????????????
        stringRedisTemplate.delete("getSCA");
        //???????????????Appraise???
        AppraiseDAO appraiseDAO = new AppraiseDAO();
        BeanUtils.copyProperties(studentCourseAppraiseInsertDTO,appraiseDAO);
        appraiseDAO.setAcontent(studentCourseAppraiseInsertDTO.getContent());
        int insertRow = appraiseMapper.insert(appraiseDAO);

        QueryWrapper maxid = new QueryWrapper();
        maxid.orderByDesc("ano");
        List<AppraiseDAO> maxids= appraiseMapper.selectList(maxid);

        if(insertRow >= 1){
            //???????????????sca???studentCourseApprase???
            StudentCourseAppraiseDAO studentCourseAppraiseDAO = new StudentCourseAppraiseDAO();
            BeanUtils.copyProperties(studentCourseAppraiseInsertDTO,studentCourseAppraiseDAO);
            studentCourseAppraiseDAO.setAno(maxids.get(0).getAno());
            int insert = studentCourseAppraiseMapper.insert(studentCourseAppraiseDAO);
            if(insert>=1){
                //?????????????????????????????????
                //????????????????????????ano??????
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("cno",studentCourseAppraiseInsertDTO.getCno());
                List<StudentCourseAppraiseDAO> studentCourseAppraiseDAOS = studentCourseAppraiseMapper.selectList(queryWrapper);
                int cnt = 0;
                float sum = 0;
                for(StudentCourseAppraiseDAO sca:studentCourseAppraiseDAOS){
                    //??????ano??????astar
                    QueryWrapper getStarQW = new QueryWrapper();
                    getStarQW.eq("ano",sca.getAno());
                    AppraiseDAO starAppraiseDAO = appraiseMapper.selectOne(getStarQW);
                    sum += starAppraiseDAO.getAstar();
                    cnt++;
                }
                QueryWrapper updateQW = new QueryWrapper();
                updateQW.eq("cno",studentCourseAppraiseInsertDTO.getCno());
                CourseDAO courseDAO = new CourseDAO();
                courseDAO.setAveragestar(new Float(sum/cnt));
                courseMapper.update(courseDAO,updateQW);

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
    public List<AppraiseVO> getCourseAppraise(Integer courseId) {
        //????????????????????????ano??????
        List<String> valueList = stringRedisTemplate.opsForList().range( "getSCA", 0, -1);
        List<StudentCourseAppraiseDAO> studentCourseAppraiseDAOS = new ArrayList<>();
        if(ObjectUtils.isEmpty(valueList)){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("cno",courseId);
            studentCourseAppraiseDAOS = studentCourseAppraiseMapper.selectList(queryWrapper);

            ObjectMapper objectMapper = new ObjectMapper();
            for (StudentCourseAppraiseDAO studentCourseAppraiseDAO:studentCourseAppraiseDAOS){
                try {
                    String jsonString = objectMapper.writeValueAsString(studentCourseAppraiseDAO);
                    stringRedisTemplate.opsForList().rightPush("getSCA",jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();

            for(String value:valueList){
                try {
                    StudentCourseAppraiseDAO studentCourseAppraiseDAO = objectMapper.readValue(value, StudentCourseAppraiseDAO.class);
                    studentCourseAppraiseDAOS.add(studentCourseAppraiseDAO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        }

        //????????????????????????
        ArrayList<AppraiseVO> appraiseVOList = new ArrayList<>();
        for(StudentCourseAppraiseDAO studentCourseAppraiseDAO:studentCourseAppraiseDAOS){
            AppraiseDAO appraiseDAO = new AppraiseDAO();
            String getAno = stringRedisTemplate.opsForValue().get("ano"+String.valueOf(studentCourseAppraiseDAO.getAno()));
            if(ObjectUtils.isEmpty(getAno)){
                QueryWrapper appraiseDetailQW = new QueryWrapper();
                appraiseDetailQW.eq("ano",studentCourseAppraiseDAO.getAno());
                appraiseDAO = appraiseMapper.selectOne(appraiseDetailQW);

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(appraiseDAO);
                    stringRedisTemplate.opsForValue().set("ano"+String.valueOf(studentCourseAppraiseDAO.getAno()),jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    appraiseDAO = objectMapper.readValue(getAno, AppraiseDAO.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }


            AppraiseVO appraiseVO = new AppraiseVO();
            BeanUtils.copyProperties(appraiseDAO,appraiseVO);
            //??????sno??????????????????
            StudentDAO studentDAO = new StudentDAO();
            String getSno = stringRedisTemplate.opsForValue().get("sno"+String.valueOf(studentCourseAppraiseDAO.getSno()));
            if(ObjectUtils.isEmpty(getSno)){
                QueryWrapper getUsernameQW = new QueryWrapper();
                getUsernameQW.eq("sno",studentCourseAppraiseDAO.getSno());
                studentDAO = studentMapper.selectOne(getUsernameQW);

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(studentDAO);
                    stringRedisTemplate.opsForValue().set("sno"+String.valueOf(studentCourseAppraiseDAO.getSno()),jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    studentDAO = objectMapper.readValue(getSno, StudentDAO.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            appraiseVO.setUsername(studentDAO.getUsername());
            //??????cno???sno??????tno
            QueryWrapper getTnoQW = new QueryWrapper();
            getTnoQW.eq("cno",studentCourseAppraiseDAO.getCno());
            getTnoQW.eq("sno",studentCourseAppraiseDAO.getSno());
            StudentCourseTeacherDAO studentCourseTeacherDAO = studentCourseTeacherMapper.selectOne(getTnoQW);
            Integer tno = studentCourseTeacherDAO.getTno();
            //??????tno??????tname
            QueryWrapper getTnameQW = new QueryWrapper();
            getTnameQW.eq("tno",tno);
            TeacherDAO teacherDAO = teacherMapper.selectOne(getTnameQW);
            appraiseVO.setTname(teacherDAO.getTname());

            appraiseVOList.add(appraiseVO);
        }
        return appraiseVOList;
    }

    @Autowired
    StudentCourseTeacherCurrentMapper currentMapper;
    @Override
    public List<CourseVO> passConflictCourse(Integer page,Integer num, Integer sno) {
        List<CourseDAO> courseDAOS = courseMapper.selectList(null);

        ArrayList<CourseVO> courseVOS = new ArrayList<>();
        for (CourseDAO courseDAO:courseDAOS){
            //???????????????????????????
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("kno",courseDAO.getKno());
            KindDAO kindDAO = kindMapper.selectOne(queryWrapper);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(courseDAO,courseVO);
            courseVO.setkName(kindDAO.getKname());
            courseVOS.add(courseVO);
        }
        QueryWrapper currentQuery = new QueryWrapper();
        currentQuery.eq("sno",sno);
        List<StudentCourseTeacherCurrentDAO> currentList= currentMapper.selectList(currentQuery);

        for(int i = 0; i < currentList.size(); i++){
            //?????????????????????
            Integer cno = currentList.get(i).getCno();
            courseVOS.removeIf(s->s.getCno().equals(cno));
        }
        ArrayList<CourseVO> list = new ArrayList<>();
        for(int i = (page-1)*num; i < courseVOS.size(); i++){
            list.add(courseVOS.get(i));
            if(list.size() == num) break;
        }
        return list;
    }

    @Override
    public List<CourseVO> getKnoCourse(Integer page, Integer num, Integer kno) {
        QueryWrapper kindQuery = new QueryWrapper();
        kindQuery.eq("kno", kno);
        List<CourseDAO> courseDAOS = courseMapper.selectList(kindQuery);
        ArrayList<CourseVO> courseVOS = new ArrayList<>();
        for (CourseDAO courseDAO:courseDAOS){
            //???????????????????????????
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("kno",kno);
            KindDAO kindDAO = kindMapper.selectOne(queryWrapper);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(courseDAO,courseVO);
            courseVO.setkName(kindDAO.getKname());
            courseVOS.add(courseVO);
        }
        ArrayList<CourseVO> list = new ArrayList<>();
        for(int i = (page-1)*num; i < courseVOS.size(); i++){
            list.add(courseVOS.get(i));
            if(list.size() == num) break;
        }
        return list;
    }
}
