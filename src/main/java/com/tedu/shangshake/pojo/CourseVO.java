package com.tedu.shangshake.pojo;

public class CourseVO {
    Integer cno;
    String cname;
    Float credit;
    Float averagestar;
    Integer kNo;
    String kName;

    public Float getAveragestar() {
        return averagestar;
    }

    public void setAveragestar(Float averagestar) {
        this.averagestar = averagestar;
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

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Integer getkNo() {
        return kNo;
    }

    public void setkNo(Integer kNo) {
        this.kNo = kNo;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }
}
