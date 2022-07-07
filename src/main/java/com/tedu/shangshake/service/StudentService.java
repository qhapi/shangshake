package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.*;

import java.util.List;

public interface StudentService {
    Integer login(StudentLoginDTO studentLoginDTO);
    Integer register(StudentRegisterDTO studentRegisterDTO);
    StudentVO getStudentInfo(Integer sno);
    Boolean changePassword(StudentChangePasswordDTO studentChangePasswordDTO);
    Boolean updateStudentInfo(StudentUpdateDTO studentUpdateDTO);
    List<SelfCourseVO> getSelfCourseTable(Integer sno);
}
