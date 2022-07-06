package com.tedu.shangshake.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;

@TableName("sctcurrent")
public class StudentCourseTeacherCurrentDAO {
    Integer sno;
    Integer cno;
    Integer tno;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }
}
