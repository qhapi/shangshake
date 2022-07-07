package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.ServerResult;
import com.tedu.shangshake.pojo.TeacherDetailVO;
import com.tedu.shangshake.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @RequestMapping("/teacher/getTeacherDetail")
    public ServerResult getTeacherDetail(Integer teacherId){
        TeacherDetailVO teacherDetail = teacherService.getTeacherDetail(teacherId);
        ServerResult serverResult = new ServerResult(0, "getTeacherDetail success", teacherDetail);
        return serverResult;
    }
}
