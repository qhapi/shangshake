package com.tedu.shangshake.service.impl;

import com.tedu.shangshake.mapper.CourseMapper;
import com.tedu.shangshake.mapper.SpecialtyKindCreditMapper;
import com.tedu.shangshake.mapper.SpecialtyMapper;
import com.tedu.shangshake.mapper.StudentMapper;
import com.tedu.shangshake.pojo.AllConditionVO;
import com.tedu.shangshake.pojo.CourseDAO;
import com.tedu.shangshake.pojo.CurrentConditionDTO;
import com.tedu.shangshake.pojo.CurrentConditionVO;
import com.tedu.shangshake.service.StudentCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public AllConditionVO getAllCondition(Integer no) {
        return null;
    }

    @Override
    public List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto) {
        return null;
    }
}
