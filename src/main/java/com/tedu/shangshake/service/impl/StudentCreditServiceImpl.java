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
    @Autowired
    StudentCourseTeacherCurrentMapper studentCourseTeacherCurrentMapper;

    Map<Integer, Integer> cPassMap;
    ArrayList<AllConditionVO> allConditionVOList;

    //输入学生no，返回学生的总的学分修读情况。
    //QueryWrapper并不能真正的多表查询。
    @Override
    public List<AllConditionVO> getAllCondition(Integer no) {
        ArrayList<AllConditionVO> voList = new ArrayList<>();//要返回的数据的集合。

        //先通过student表查出专业号
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno", no);
        StudentDAO studentDAO = studentMapper.selectOne(queryWrapper);
        Integer spNo = studentDAO.getSpno();
        //再通过专业号查出专业对应的总学分列表
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("spno", spNo);
        List<SpecialtyKindCreditDAO> spkcDAOList = specialtyKindCreditMapper.selectList(queryWrapper);

        //获取学生学到的所有课程，累加指定类型的课程的学分
        List<KindDAO> kindDAOList = kindMapper.selectList(null);
        Map<Integer, Float> kindCreditMap = new HashMap<>();//k是kno,v是credit
        for (KindDAO kindDAO : kindDAOList) {
            kindCreditMap.put(kindDAO.getKno(), 0f);
        }
        //获取学生选的所有课程编号（和是否通过）
        Map<Integer, Integer> cPassMap = new HashMap<>();
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("sno", no);
        List<StudentCourseTeacherDAO> sctDAOList = studentCourseTeacherMapper.selectList(null);
        for (StudentCourseTeacherDAO sctDAO : sctDAOList) {
            cPassMap.put(sctDAO.getCno(), sctDAO.getPassed());
        }
        //遍历cPassMap,将指定课程的类别号和kindCreditMap比较，如匹配，加上相应学分
        for (Integer cno : cPassMap.keySet()) {
            Integer passed = cPassMap.get(cno);
            if (passed == 1) {
                //如果通过，找到课程类型编号
                queryWrapper = new QueryWrapper();
                queryWrapper.eq("cno", cno);
                CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);//
                Integer kind = courseDAO.getKno();
                Float credit = courseDAO.getCredit();
                Float creditSum = kindCreditMap.get(kind) + credit;
                kindCreditMap.put(kind, creditSum);
            }
        }
        //最后算正修读的学分
        Map<Integer, Float> kindCurrCreditMap = new HashMap<>();
        for (KindDAO kindDAO : kindDAOList) {
            kindCurrCreditMap.put(kindDAO.getKno(), 0f);
        }
        List<StudentCourseTeacherCurrentDAO> sctCurrDAOList = studentCourseTeacherCurrentMapper.selectList(null);
        for (StudentCourseTeacherCurrentDAO sctCurrDAO : sctCurrDAOList) {
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("cno", sctCurrDAO.getCno());
            CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);
            Integer kind = courseDAO.getKno();
            Float credit = courseDAO.getCredit();
            Float creditSum = kindCurrCreditMap.get(kind) + credit;
            kindCurrCreditMap.put(kind, creditSum);
        }

        //遍历类型（从spkcDAOList定义的地方移了过来）
        for (SpecialtyKindCreditDAO spkcDAO : spkcDAOList) {
            //查询出类型名
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("kno", spkcDAO.getKno());
            KindDAO kindDAO = kindMapper.selectOne(queryWrapper);

            AllConditionVO acVO = new AllConditionVO();
            acVO.setkName(kindDAO.getKname());//设置类型名
            acVO.setAllCredit(spkcDAO.getCredit());//设置总学分
            //将kindCreditMap中的分数加到已修学分中
            acVO.setHistoryCredit(kindCreditMap.get(spkcDAO.getKno()));
            acVO.setCurrentCredit(kindCurrCreditMap.get(spkcDAO.getKno()));
            voList.add(acVO);
        }

        this.cPassMap = cPassMap;
        this.allConditionVOList = voList;

        return voList;
    }

    @Override
    public List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto) {
        //课程号、课程名、星级、学分、介绍、图像、是否通过
        ArrayList<CurrentConditionVO> voList = new ArrayList<>();
        //dot中有（学生编号）sNo和（类型编号）kNo
        //this.cPassMap中有该学生所有已选课程的是否通过情况。
        for (Integer cno : this.cPassMap.keySet()) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("cno", cno);
            CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);
            CurrentConditionVO vo = new CurrentConditionVO();
            vo.setNo(cno);
            vo.setName(courseDAO.getCname());
            vo.setAverageStar(courseDAO.getAveragestar());
            vo.setCredit(courseDAO.getCredit());
            vo.setIntroduction(courseDAO.getCintroduction());
            vo.setPicture(courseDAO.getCpicture());
            vo.setPassed(this.cPassMap.get(cno));
            voList.add(vo);
        }

        return voList;
    }

    //返回指定类型课程修读页面的标题
    @Override
    public AllConditionVO getConditionTitle(CurrentConditionDTO dto) {
        AllConditionVO vo = new AllConditionVO();
        //查出dto中kno对应的类型名
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("kno", dto.kNo);
        KindDAO kindDAO = kindMapper.selectOne(queryWrapper);
        String kName = kindDAO.getKname();
        vo.setkName(kName);

        for (AllConditionVO acVO : allConditionVOList) {
            if (acVO.getkName().equals(vo.getkName())) {
                vo.setAllCredit(acVO.getAllCredit());
                vo.setHistoryCredit(acVO.getHistoryCredit());
                vo.setCurrentCredit(acVO.getCurrentCredit());
                break;
            }
        }

        return vo;
    }
}
