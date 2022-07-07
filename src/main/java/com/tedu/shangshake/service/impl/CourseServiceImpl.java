package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.*;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<CourseVO> getCourse() {
        List<CourseDAO> courseDAOS = courseMapper.selectList(null);
        ArrayList<CourseVO> courseVOS = new ArrayList<>();
        for (CourseDAO courseDAO:courseDAOS){
            //获取到课程类别名称
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("kno",courseDAO.getKno());
            KindDAO kindDAO = kindMapper.selectOne(queryWrapper);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(courseDAO,courseVO);
            courseVO.setkName(kindDAO.getKname());
            courseVOS.add(courseVO);
        }
        return courseVOS;
    }

    @Override
    public List<CourseDetailVO> getCourseDetail(Integer courseId) {
        //获取课程的基本信息
        QueryWrapper courseInfoQW = new QueryWrapper();
        courseInfoQW.eq("cno",courseId);
        CourseDAO courseDAO = courseMapper.selectOne(courseInfoQW);
        //根据courseId在教师课程表中找教师课程list
        QueryWrapper tcListQW = new QueryWrapper();
        tcListQW.eq("cno",courseDAO.getCno());
        List<CourseTeacherDAO> courseTeacherDAOList=courseTeacherMapper.selectList(tcListQW);

        List<CourseDetailVO> courseDetailVOList = new LinkedList<>();

        for(CourseTeacherDAO courseTeacherDAO:courseTeacherDAOList){
            CourseDetailVO courseDetailVO = new CourseDetailVO();
            BeanUtils.copyProperties(courseDAO,courseDetailVO);
            BeanUtils.copyProperties(courseTeacherDAO,courseDetailVO);
            //根据tno获取tname
            QueryWrapper getTnameQW = new QueryWrapper();
            getTnameQW.eq("tno",courseTeacherDAO.getTno());
            TeacherDAO teacherDAO = teacherMapper.selectOne(getTnameQW);

            courseDetailVO.setTname(teacherDAO.getTname());

            courseDetailVOList.add(courseDetailVO);
        }

        return courseDetailVOList;
    }

    @Override
    public boolean addCourseAppraise(StudentCourseAppraiseInsertDTO studentCourseAppraiseInsertDTO) {
        //插入信息到Appraise表
        AppraiseDAO appraiseDAO = new AppraiseDAO();
        BeanUtils.copyProperties(studentCourseAppraiseInsertDTO,appraiseDAO);
        appraiseDAO.setAcontent(studentCourseAppraiseInsertDTO.getContent());
        int insertRow = appraiseMapper.insert(appraiseDAO);
        Integer ano = appraiseDAO.getAno();
        if(insertRow >= 1){
            return true;

        }else
            return false;
        //插入信息到sca：studentCourseApprase表

    }
}
