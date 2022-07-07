package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.CourseDetailVO;
import com.tedu.shangshake.pojo.CourseVO;
import com.tedu.shangshake.pojo.ServerResult;
import com.tedu.shangshake.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
