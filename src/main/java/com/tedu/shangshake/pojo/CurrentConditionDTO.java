package com.tedu.shangshake.pojo;

public class CurrentConditionDTO {
    public Integer sNo;//学生编号
    public Integer kNo;//类型编号

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public Integer getkNo() {
        return kNo;
    }

    public void setkNo(Integer kNo) {
        this.kNo = kNo;
    }
}
