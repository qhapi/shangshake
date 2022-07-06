package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("spkcredit")
public class SpecialtyKindCreditDAO {
    Integer spno;
    Integer kno;
    Float credit;

    public Integer getSpno() {
        return spno;
    }

    public void setSpno(Integer spno) {
        this.spno = spno;
    }

    public Integer getKno() {
        return kno;
    }

    public void setKno(Integer kno) {
        this.kno = kno;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }
}
