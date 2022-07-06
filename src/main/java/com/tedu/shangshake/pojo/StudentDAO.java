package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class StudentDAO {
    Integer sno;
    Integer spno;
    String sname;
    String username;
    String password;
    String phonenumber;
    String spicture;
    String sex;
    String grade;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getSpno() {
        return spno;
    }

    public void setSpno(Integer spno) {
        this.spno = spno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phonenumber;
    }

    public void setPhone_number(String phone_number) {
        this.phonenumber = phone_number;
    }

    public String getS_picture() {
        return spicture;
    }

    public void setS_picture(String s_picture) {
        this.spicture = s_picture;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
