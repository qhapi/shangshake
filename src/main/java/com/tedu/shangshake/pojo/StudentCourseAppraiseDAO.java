package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sca")
public class StudentCourseAppraiseDAO {
    Integer sno;
    Integer cno;
    Integer ano;

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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
