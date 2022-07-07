package com.tedu.shangshake.pojo;

public class StudentCourseAppraiseInsertDTO {
    Integer sno;
    Integer cno;
    String content;
    Boolean isanonymous;
    Float astar;

    public Boolean getIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(Boolean isanonymous) {
        this.isanonymous = isanonymous;
    }

    public Float getAstar() {
        return astar;
    }

    public void setAstar(Float astar) {
        this.astar = astar;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
