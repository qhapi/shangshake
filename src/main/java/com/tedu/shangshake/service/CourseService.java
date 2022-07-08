package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.AppraiseVO;
import com.tedu.shangshake.pojo.CourseDetailVO;
import com.tedu.shangshake.pojo.CourseVO;
import com.tedu.shangshake.pojo.StudentCourseAppraiseInsertDTO;

import java.util.List;

public interface CourseService {
    public List<CourseVO> getCourse(Integer page, Integer num);
    public List<CourseDetailVO> getCourseDetail(Integer courseId);
    public boolean addCourseAppraise(StudentCourseAppraiseInsertDTO studentCourseAppraiseInsertDTO);
    public List<AppraiseVO> getCourseAppraise(Integer courseId);
    public List<CourseVO> passConflictCourse(Integer page, Integer num, Integer sno);
    public List<CourseVO> getKnoCourse(Integer page, Integer num, Integer kno);
}
