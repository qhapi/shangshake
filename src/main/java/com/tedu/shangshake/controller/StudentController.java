package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.ServerResult;
import com.tedu.shangshake.pojo.StudentLoginDTO;
import com.tedu.shangshake.pojo.StudentRegisterDTO;
import com.tedu.shangshake.pojo.StudentVO;
import com.tedu.shangshake.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 戚桂岳负责
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/login")
    public ServerResult login(StudentLoginDTO studentLoginDTO){
        Integer sno = studentService.login(studentLoginDTO);
        if(sno != -1)
            return new ServerResult(0,"login success",sno);
        else
            return new ServerResult(-1,"login failed",-1);

    }

    @RequestMapping("/register")
    public ServerResult register(StudentRegisterDTO studentRegisterDTO){
        Integer sno = studentService.register(studentRegisterDTO);
        if(sno != -1)
            return new ServerResult(0,"register success",sno);
        else
            return new ServerResult(-1,"register failed:no such id",-1);
    }

    @RequestMapping("/getStudentInfo")
    public  ServerResult getStudentInfo(Integer sno)
    {
        StudentVO studentInfo = studentService.getStudentInfo(sno);
        if(studentInfo != null)
            return new ServerResult(0,"success",studentInfo);
        else
            return new ServerResult(-1,"failed:no such id",null);


    }
}
