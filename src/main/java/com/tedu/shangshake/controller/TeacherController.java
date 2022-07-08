package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.ServerResult;
import com.tedu.shangshake.pojo.StudentTeacherAppraiseInsertDTO;
import com.tedu.shangshake.pojo.TeacherAppraiseVO;
import com.tedu.shangshake.pojo.TeacherDetailVO;
import com.tedu.shangshake.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
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
    @RequestMapping("/teacher/getTeacherAppraise")
    public ServerResult getTeacherAppraise(Integer teacherId){
        List<TeacherAppraiseVO> teacherAppraise = teacherService.getTeacherAppraise(teacherId);
        ServerResult serverResult = new ServerResult(0, "getTeacherAppraise success",teacherAppraise);
        return serverResult;
    }
    @RequestMapping("/teacher/addTeacherAppraise")
    public ServerResult addTeacherAppraise(StudentTeacherAppraiseInsertDTO studentTeacherAppraiseInsertDTO){
        boolean b = teacherService.addTeacherAppraise(studentTeacherAppraiseInsertDTO);
        if(b){
            return new ServerResult(0,"addTeacherAppraise success",null);
        }else {
            return new ServerResult(1,"addTeacherAppraise fail",null);
        }
    }
}
