package com.tedu.shangshake.pojo;

import java.util.Date;

public class AppraiseVO {
    String acontent;
    Float astar;
    Date atime;
    Boolean isanonymous;
    String tname;
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public Boolean getIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(Boolean isanonymous) {
        this.isanonymous = isanonymous;
    }
}
