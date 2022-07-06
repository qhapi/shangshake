package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sta")
public class StudentTeacherAppraiseDAO {
    Integer sno;
    Integer tno;
    Integer ano;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
