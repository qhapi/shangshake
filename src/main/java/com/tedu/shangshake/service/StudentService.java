package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.StudentLoginDTO;
import com.tedu.shangshake.pojo.StudentRegisterDTO;

public interface StudentService {
    Integer login(StudentLoginDTO studentLoginDTO);
    Integer register(StudentRegisterDTO studentRegisterDTO);
}
