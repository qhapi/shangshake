package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;

@TableName("ct")
public class CourseTeacherDAO {
    Integer cno;
    Integer tno;
    Integer cbeginweek;
    Integer cendweek;
    Integer week;//周次
    Integer section;//节次
    String testmethod;
    String teachplace;
    String teachmethod;
    String remark;

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public Integer getCbeginweek() {
        return cbeginweek;
    }

    public void setCbeginweek(Integer cbeginweek) {
        this.cbeginweek = cbeginweek;
    }

    public Integer getCendweek() {
        return cendweek;
    }

    public void setCendweek(Integer cendweek) {
        this.cendweek = cendweek;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getTestmethod() {
        return testmethod;
    }

    public void setTestmethod(String testmethod) {
        this.testmethod = testmethod;
    }

    public String getTeachplace() {
        return teachplace;
    }

    public void setTeachplace(String teachplace) {
        this.teachplace = teachplace;
    }

    public String getTeachmethod() {
        return teachmethod;
    }

    public void setTeachmethod(String teachmethod) {
        this.teachmethod = teachmethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
