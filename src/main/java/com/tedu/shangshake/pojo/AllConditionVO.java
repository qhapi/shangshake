package com.tedu.shangshake.pojo;

//在学分完成情况（需修学分）页面显示
//不要用List当数据类型
//需修学分 = 总学分 - 修完的学分 - 正修读学分
public class AllConditionVO {
    public String kName;//课程类型名
    public float allCredit;//当前课程类型的总学分
    public float historyCredit;//修完的学分
    public float currentCredit;//正修读学分

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public float getAllCredit() {
        return allCredit;
    }

    public void setAllCredit(float allCredit) {
        this.allCredit = allCredit;
    }

    public float getHistoryCredit() {
        return historyCredit;
    }

    public void setHistoryCredit(float historyCredit) {
        this.historyCredit = historyCredit;
    }

    public float getCurrentCredit() {
        return currentCredit;
    }

    public void setCurrentCredit(float currentCredit) {
        this.currentCredit = currentCredit;
    }
}
