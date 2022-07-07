package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("/course/getCourse")
    public ServerResult getCourse(){
        List<CourseVO> course = courseService.getCourse();
        ServerResult serverResult = new ServerResult(0, "getCourse success", course);
        return serverResult;
    }
    @RequestMapping("/course/getCourseDetail")
    public ServerResult getCourseDetail(Integer courseId){
        List<CourseDetailVO> courseDetail = courseService.getCourseDetail(courseId);
        ServerResult serverResult = new ServerResult(0, "getCourseDetail success", courseDetail);
        return serverResult;
    }
    @RequestMapping("/course/addCourseAppraise")
    public ServerResult addCourseAppraise(StudentCourseAppraiseInsertDTO studentCourseAppraiseInsertDTO){
        boolean isSuccess = courseService.addCourseAppraise(studentCourseAppraiseInsertDTO);
        if(isSuccess){
            return new ServerResult(0,"addCourseAppraise success",null);
        }else {
            return new ServerResult(1,"addCourseAppraise false",null);
        }
    }
    @RequestMapping("/course/getCourseAppraise")
    public ServerResult getCourseAppraise(Integer courseId){
        List<AppraiseVO> courseAppraise = courseService.getCourseAppraise(courseId);
        ServerResult serverResult = new ServerResult(0, "getCourseAppraise success", courseAppraise);
        return serverResult;
    }
}
