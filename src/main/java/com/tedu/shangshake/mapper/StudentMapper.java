package com.tedu.shangshake.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.shangshake.pojo.StudentDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<StudentDAO> {
}
