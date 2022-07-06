package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.StudentLoginDTO;
import com.tedu.shangshake.pojo.StudentRegisterDTO;
import com.tedu.shangshake.pojo.StudentVO;

public interface StudentService {
    Integer login(StudentLoginDTO studentLoginDTO);
    Integer register(StudentRegisterDTO studentRegisterDTO);
    StudentVO getStudentInfo(Integer sno);
}
