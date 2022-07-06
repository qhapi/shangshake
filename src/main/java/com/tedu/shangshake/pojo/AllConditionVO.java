package com.tedu.shangshake.pojo;

//在学分完成情况（需修学分）页面显示
//不要用List当数据类型
//需修学分 = 总学分 - 修完的学分 - 正修读学分
public class AllConditionVO {
    public String kName;//课程类型名
    public Integer allCredit;//当前课程类型的总学分
    public Integer historyCredit;//修完的学分
    public Integer currentCredit;//正修读学分

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public Integer getAllCredit() {
        return allCredit;
    }

    public void setAllCredit(Integer allCredit) {
        this.allCredit = allCredit;
    }

    public Integer getHistoryCredit() {
        return historyCredit;
    }

    public void setHistoryCredit(Integer historyCredit) {
        this.historyCredit = historyCredit;
    }

    public Integer getCurrentCredit() {
        return currentCredit;
    }

    public void setCurrentCredit(Integer currentCredit) {
        this.currentCredit = currentCredit;
    }
}
