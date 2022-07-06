package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.SpecialtyMapper;
import com.tedu.shangshake.mapper.StudentMapper;
import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
