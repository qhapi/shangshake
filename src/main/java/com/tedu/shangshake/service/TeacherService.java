package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.TeacherAppraiseVO;
import com.tedu.shangshake.pojo.StudentTeacherAppraiseInsertDTO;
import com.tedu.shangshake.pojo.TeacherDetailVO;

import java.util.List;

public interface TeacherService {
    public TeacherDetailVO getTeacherDetail(Integer teacherId);
    public boolean addTeacherAppraise(StudentTeacherAppraiseInsertDTO studentTeacherAppraiseInsertDTO);
    public List<TeacherAppraiseVO> getTeacherAppraise(Integer teacherId);
}
