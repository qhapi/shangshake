package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.*;
import com.tedu.shangshake.service.StudentCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StudentCreditController {
    @Autowired
    StudentCreditService studentCreditService;

    @RequestMapping("/credit/allCondition")
    public ServerResult allCondition(Integer no) {
        List<AllConditionVO> list = studentCreditService.getAllCondition(no);
        if (list.size() == 0) {
            return new ServerResult(0, "出错了", list);
        } else {
            return new ServerResult(0, "成功", list);
        }
    }

    @RequestMapping("/credit/currentCondition")
    public ServerResult currentCondition(CurrentConditionDTO dto) {
        List<CurrentConditionVO> list = studentCreditService.getCurrentCondition(dto);
        AllConditionVO conTitle = studentCreditService.getConditionTitle(dto);
        CurrentConditionTitleVO ccTitleVO = new CurrentConditionTitleVO();
        ccTitleVO.setVoList(list);
        ccTitleVO.setTitle(conTitle);

        ServerResult serverResult = new ServerResult(0, "成功", ccTitleVO);
        return serverResult;
    }
}
