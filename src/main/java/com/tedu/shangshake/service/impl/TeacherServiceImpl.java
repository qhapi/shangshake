package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.AppraiseMapper;
import com.tedu.shangshake.mapper.StudentTeacherAppraiseMapper;
import com.tedu.shangshake.mapper.TeacherMapper;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TeacherDetailVO getTeacherDetail(Integer teacherId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tno",teacherId);
        TeacherDAO teacherDAO = teacherMapper.selectOne(queryWrapper);

        TeacherDetailVO teacherDetailVO = new TeacherDetailVO();
        BeanUtils.copyProperties(teacherDAO,teacherDetailVO);
        return teacherDetailVO;
    }

    @Override
    public boolean addTeacherAppraise(StudentTeacherAppraiseInsertDTO studentTeacherAppraiseInsertDTO) {

        return false;
    }

    @Override
    public List<TeacherAppraiseVO> getTeacherAppraise(Integer teacherId) {
        //获取到教师的评论ano集合
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tno",teacherId);
        List<StudentTeacherAppraiseDAO> studentTeacherAppraiseDAOS = studentTeacherAppraiseMapper.selectList(queryWrapper);
        //获取评价详细信息
        ArrayList<TeacherAppraiseVO> teacherAppraiseVOArrayList = new ArrayList<>();
        for(StudentTeacherAppraiseDAO studentTeacherAppraiseDAO:studentTeacherAppraiseDAOS){
            QueryWrapper appraiseDetailQW = new QueryWrapper();
            appraiseDetailQW.eq("ano",studentTeacherAppraiseDAO.getAno());
            AppraiseDAO appraiseDAO = appraiseMapper.selectOne(appraiseDetailQW);

            TeacherAppraiseVO teacherAppraiseVO = new TeacherAppraiseVO();
            BeanUtils.copyProperties(appraiseDAO,teacherAppraiseVO);
            teacherAppraiseVOArrayList.add(teacherAppraiseVO);
        }
        return teacherAppraiseVOArrayList;
    }
}
