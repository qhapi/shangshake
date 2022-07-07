package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.CourseDetailVO;
import com.tedu.shangshake.pojo.CourseVO;
import com.tedu.shangshake.pojo.StudentCourseAppraiseInsertDTO;

import java.util.List;

public interface CourseService {
    public List<CourseVO> getCourse();
    public List<CourseDetailVO> getCourseDetail(Integer courseId);
    public boolean addCourseAppraise(StudentCourseAppraiseInsertDTO studentCourseAppraiseInsertDTO);
}
