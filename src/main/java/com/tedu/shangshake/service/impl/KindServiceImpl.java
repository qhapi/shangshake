package com.tedu.shangshake.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.shangshake.mapper.KindMapper;
import com.tedu.shangshake.pojo.KindDAO;
import com.tedu.shangshake.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindServiceImpl implements KindService {
    @Autowired
    KindMapper kindMapper;


    @Override
    public List<KindDAO> selectAllKind(){
        List<KindDAO> list = kindMapper.selectList(null);
        return list;
    }
}
