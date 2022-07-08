package com.tedu.shangshake.pojo;

import java.util.ArrayList;
import java.util.List;

public class SelfCourseList {
    public List<SelfCourseVO> list;

    public SelfCourseList() {
        this.list = new ArrayList<>();
    }

    public SelfCourseList(List<SelfCourseVO> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }
}
