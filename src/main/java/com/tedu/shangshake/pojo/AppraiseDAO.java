package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Time;

@TableName("appraise")
public class AppraiseDAO {
    Integer ano;
    String acontent;
    Float astar;
    Time atime;
    Boolean isanonymous;


    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    public Float getAstar() {
        return astar;
    }

    public void setAstar(Float astar) {
        this.astar = astar;
    }

    public Time getAtime() {
        return atime;
    }

    public void setAtime(Time atime) {
        this.atime = atime;
    }

    public Boolean getIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(Boolean isanonymous) {
        this.isanonymous = isanonymous;
    }
}
