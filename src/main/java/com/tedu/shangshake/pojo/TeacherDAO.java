package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("teacher")
public class TeacherDAO {
    Integer tno;
    String tname;
    String duty;//职务
    String t_edu_rec;//学位
    String tpicture;
    String tintroduction;
    String contact;

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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getT_edu_rec() {
        return t_edu_rec;
    }

    public void setT_edu_rec(String t_edu_rec) {
        this.t_edu_rec = t_edu_rec;
    }

    public String getTpicture() {
        return tpicture;
    }

    public void setTpicture(String tpicture) {
        this.tpicture = tpicture;
    }

    public String getTintroduction() {
        return tintroduction;
    }

    public void setTintroduction(String tintroduction) {
        this.tintroduction = tintroduction;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
