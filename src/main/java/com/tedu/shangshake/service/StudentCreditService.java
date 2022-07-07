package com.tedu.shangshake.service;

import com.tedu.shangshake.pojo.AllConditionVO;
import com.tedu.shangshake.pojo.CurrentConditionDTO;
import com.tedu.shangshake.pojo.CurrentConditionVO;

import java.util.List;

public interface StudentCreditService {
    List<AllConditionVO> getAllCondition(Integer no);
    List<CurrentConditionVO> getCurrentCondition(CurrentConditionDTO dto);
}
