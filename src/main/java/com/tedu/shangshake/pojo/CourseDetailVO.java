package com.tedu.shangshake.pojo;

public class CourseDetailVO {
    String cname;
    Float averageStar;
    Float credit;
    String cintroduction;
    String cpicture;

    Integer tno;
    String tname;
    Integer cbeginweek;
    Integer cendweek;
    Integer week;
    Integer section;
    String testmethod;
    String teachplace;
    String teachmethod;
    String remark;

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Float getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(Float averageStar) {
        this.averageStar = averageStar;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public String getCintroduction() {
        return cintroduction;
    }

    public void setCintroduction(String cintroduction) {
        this.cintroduction = cintroduction;
    }

    public String getCpicture() {
        return cpicture;
    }

    public void setCpicture(String cpicture) {
        this.cpicture = cpicture;
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
