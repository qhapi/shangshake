package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.*;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    KindMapper kindMapper;
    @Autowired
    StudentCourseTeacherMapper studentCourseTeacherMapper;

    //输入学生no，返回学生的总的学分修读情况。
    //QueryWrapper并不能真正的多表查询。
    @Override
    public List<AllConditionVO> getAllCondition(Integer no) {
        ArrayList<AllConditionVO> voList = new ArrayList<>();//要返回的数据的集合。

        //先通过student表查出专业号
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("no", no);
        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        Integer spNo = studentDAO.getSpno();
        //再通过专业号查出专业对应的总学分列表
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("spno", spNo);
        List<SpecialtyKindCreditDAO> spkcDAOList = specialtyKindCreditMapper.selectList(queryWrapper);

        //遍历类型
        for (SpecialtyKindCreditDAO spkcDAO : spkcDAOList) {
            //查询出专业名
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("no", spkcDAO.getKno());
            SpecialtyDAO specialtyDAO = specialtyMapper.selectOne(queryWrapper);
            AllConditionVO acVO = new AllConditionVO();
            acVO.setkName(specialtyDAO.getSpname());//设置专业名
            acVO.setAllCredit(spkcDAO.getCredit());//设置总学分
        }

        //获取学生学到的所有课程，累加指定类型的课程的学分
        List<KindDAO> kindDAOList = kindMapper.selectList(null);
        Map<Integer, Float> kindCreditMap = new HashMap<>();//k是kno,v是credit
        for (KindDAO kindDAO : kindDAOList) {
            kindCreditMap.put(kindDAO.getKno(), 0f);
        }

        Map<Integer, Integer> cPassMap = new HashMap<>();
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno", no);
        List<StudentCourseTeacherDAO> sctDAOList = studentCourseTeacherMapper.selectList(null);
        for (StudentCourseTeacherDAO sctDAO : sctDAOList) {
            cPassMap.put(sctDAO.getCno(), sctDAO.getPassed());
        }

        return null;
    }

    @Override
    public List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto) {
        return null;
    }
}
