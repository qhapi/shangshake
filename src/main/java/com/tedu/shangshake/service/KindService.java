package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.AllConditionVO;
import com.tedu.shangshake.pojo.CurrentConditionDTO;
import com.tedu.shangshake.pojo.CurrentConditionVO;
import com.tedu.shangshake.pojo.KindDAO;

import java.util.List;

public interface KindService {
    public List<KindDAO> selectAllKind();
}
