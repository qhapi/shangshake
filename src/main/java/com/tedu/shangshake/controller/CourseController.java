package com.tedu.shangshake.controller;

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
        Integer j;
        List<CourseVO> course = courseService.getCourse();
        ServerResult serverResult = new ServerResult(0, "getCourse success", course);
        return serverResult;
    }
}
