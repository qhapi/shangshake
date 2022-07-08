package com.tedu.shangshake.pojo;

public class SelfCourseVO {
    Integer cno;
    String cname;
    Integer week;
    Integer section;
    Integer beginweek;
    Integer endweek;
    String teachplace;
    String tname;

    public SelfCourseVO(){}

    public SelfCourseVO(Integer cno, String cname, Integer week, Integer section, Integer startweek, Integer endweek, String teachplace,String tname) {
        this.cno = cno;
        this.cname = cname;
        this.week = week;
        this.section = section;
        this.beginweek = startweek;
        this.endweek = endweek;
        this.teachplace = teachplace;
        this.tname = tname;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public Integer getBeginweek() {
        return beginweek;
    }

    public void setBeginweek(Integer beginweek) {
        this.beginweek = beginweek;
    }

    public Integer getEndweek() {
        return endweek;
    }

    public void setEndweek(Integer endweek) {
        this.endweek = endweek;
    }

    public String getTeachplace() {
        return teachplace;
    }

    public void setTeachplace(String teachplace) {
        this.teachplace = teachplace;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
