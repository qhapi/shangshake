package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("specialty")
public class SpecialtyDAO {
    int spno;
    String spname;

    public int getSpno() {
        return spno;
    }

    public void setSpno(int spno) {
        this.spno = spno;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }
}
