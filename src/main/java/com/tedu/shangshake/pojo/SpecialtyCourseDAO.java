package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;

@TableName("spc")
public class SpecialtyCourseDAO {
    Integer spno;
    Integer cno;

    public Integer getSpno() {
        return spno;
    }

    public void setSpno(Integer spno) {
        this.spno = spno;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }
}
