package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.shangshake.mapper.*;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    @Autowired
    StringRedisTemplate stringRedisTemplate;//redis对象

    Map<Integer, Integer> cPassMap;
    ArrayList<AllConditionVO> allConditionVOList;

    //输入学生no，返回学生的总的学分修读情况。
    //QueryWrapper并不能真正的多表查询。
    @Override
    public List<AllConditionVO> getAllCondition(Integer no) {
        ArrayList<AllConditionVO> voList = new ArrayList<>();//要返回的数据的集合。
        QueryWrapper queryWrapper;

        //先通过student表查出专业号
        StudentDAO studentDAO = new StudentDAO();
        String value = stringRedisTemplate.opsForValue().get(String.valueOf(no));
        if (ObjectUtils.isEmpty(value)) {//如redis中没有
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("sno", no);
            //因为具体的查询方法不一样，所以这一部分不能全都提炼到一个函数中。
            studentDAO = studentMapper.selectOne(queryWrapper);
            saveObjInRedis(studentDAO, no);
        } else {//如redis中有，将value字符串转换为studentDAO。
            ObjectMapper objectMapper = new ObjectMapper();
            try {//如果不用继承的话，这里也是不能提炼出函数的。暂时不提练了。
                studentDAO = objectMapper.readValue(value, StudentDAO.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

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
            if (sctCurrDAO.getSno().equals(no)) {
                queryWrapper = new QueryWrapper();
                queryWrapper.eq("cno", sctCurrDAO.getCno());
                CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);
                Integer kind = courseDAO.getKno();
                Float credit = courseDAO.getCredit();
                Float creditSum = kindCurrCreditMap.get(kind) + credit;
                kindCurrCreditMap.put(kind, creditSum);
            }
        }
        //总计行
        AllConditionVO sumCreditVO = new AllConditionVO();
        sumCreditVO.setkName("总计");
        Float sumAllCredit = 0.0f;
        Float sumHistoryCredit = 0.0f;
        Float sumCurrentCredit = 0.0f;

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
            sumAllCredit += spkcDAO.getCredit();
            sumHistoryCredit += kindCreditMap.get(spkcDAO.getKno());
            sumCurrentCredit += kindCurrCreditMap.get(spkcDAO.getKno());
            voList.add(acVO);
        }

        sumCreditVO.setAllCredit(sumAllCredit);
        sumCreditVO.setHistoryCredit(sumHistoryCredit);
        sumCreditVO.setCurrentCredit(sumCurrentCredit);
        voList.add(sumCreditVO);

        this.cPassMap = cPassMap;
        this.allConditionVOList = voList;

        return voList;
    }

    @Override
    public List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto) {
        //课程号、课程名、星级、学分、介绍、图像、是否通过
        ArrayList<CurrentConditionVO> voList = new ArrayList<>();
        //dto中有（学生编号）sNo和（类型编号）kNo
        //this.cPassMap中有该学生所有已选课程的是否通过情况。
        for (Integer cno : this.cPassMap.keySet()) {
            //找出course
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("cno", cno);
            CourseDAO courseDAO = courseMapper.selectOne(queryWrapper);
            //如果course.kno和dto.kno相同
            if (courseDAO.getKno() == dto.getkNo()) {
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

    private void saveObjInRedis(Object obj, Integer no) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(obj);
            stringRedisTemplate.opsForValue().set(String.valueOf(no), jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param daoClass 要返回的DAO的类型
     * @param tableName 要查找的表名
     * @param no 要查找的主键
     * @param columnName 要查找的列名
     * @param isSelectOne 是selectOne还是selectList
     * @return
     */
    private Object getDAOWithRedis(Class daoClass, String tableName, Integer no, String columnName, boolean isSelectOne) {
        String value = stringRedisTemplate.opsForValue().get(String.valueOf(no));
        if (ObjectUtils.isEmpty(value)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq(columnName, no);
            Object dao;
            try {
                dao = daoClass.newInstance();//创建一个daoClass类型的对象
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //获取到this.Class()中的所有变量名的列表，遍历该列表，
            // 如果该变量的名称和tableName+Mapper相同，调用该变量的selectOne方法。
            Class thisClass = this.getClass();
            Field[] fs = thisClass.getDeclaredFields();
            for (Field field : fs) {
                String fieldName = field.getName();
                String mapperName = tableName + "Mapper";
                if (fieldName.equals(mapperName)) {
                    try {
                        Method method = field.getClass().getMethod("selectOne", queryWrapper.getClass());
                        try {
                            dao = method.invoke(queryWrapper);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
