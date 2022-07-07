package com.tedu.shangshake.pojo;

import java.util.List;

public class CurrentConditionTitleVO {
    public AllConditionVO title;
    public List<CurrentConditionVO> voList;

    public AllConditionVO getTitle() {
        return title;
    }

    public void setTitle(AllConditionVO title) {
        this.title = title;
    }

    public List<CurrentConditionVO> getVoList() {
        return voList;
    }

    public void setVoList(List<CurrentConditionVO> voList) {
        this.voList = voList;
    }
}
