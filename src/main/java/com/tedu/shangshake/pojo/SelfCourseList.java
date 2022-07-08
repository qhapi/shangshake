package com.tedu.shangshake.pojo;

import java.util.ArrayList;
import java.util.List;

public class SelfCourseList {
    List<SelfCourseVO> list;

    public List<SelfCourseVO> getList() {
        return list;
    }

    public void setList(List<SelfCourseVO> list) {
        list = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            this.list.add(list.get(i));
        }
    }
}
