package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.TeacherMapper;
import com.tedu.shangshake.pojo.StudentTeacherAppraiseInsertDTO;
import com.tedu.shangshake.pojo.TeacherAppraiseVO;
import com.tedu.shangshake.pojo.TeacherDAO;
import com.tedu.shangshake.pojo.TeacherDetailVO;
import com.tedu.shangshake.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
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
        return null;
    }
}
