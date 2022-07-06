package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.CourseMapper;
import com.tedu.shangshake.mapper.SpecialtyKindCreditMapper;
import com.tedu.shangshake.mapper.SpecialtyMapper;
import com.tedu.shangshake.mapper.StudentMapper;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCreditServiceImpl implements StudentCreditService {
    @Autowired
    SpecialtyMapper specialtyMapper;
    @Autowired
    SpecialtyKindCreditMapper specialtyKindCreditMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;

    //输入学生no，返回学生的总的学分修读情况。
    //QueryWrapper并不能真正的多表查询。
    @Override
    public List<AllConditionVO> getAllCondition(Integer no) {
        ArrayList<AllConditionVO> voList = new ArrayList<>();//要返回的数据的集合。

        //先通过student表查出专业号
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("no", no);
        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        Integer kNo = studentDAO.getKno();
        //再通过专业号

        return null;
    }

    @Override
    public List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto) {
        return null;
    }
}
