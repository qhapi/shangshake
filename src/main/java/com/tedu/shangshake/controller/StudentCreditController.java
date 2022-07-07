package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.AllConditionVO;
import com.tedu.shangshake.pojo.CurrentConditionDTO;
import com.tedu.shangshake.pojo.CurrentConditionVO;
import com.tedu.shangshake.pojo.ServerResult;
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
        ServerResult serverResult = new ServerResult(0, "成功", list);
        return serverResult;
    }

    @RequestMapping("/credit/currentCondition")
    public ServerResult currentCondition(CurrentConditionDTO dto) {
        List<CurrentConditionVO> list = studentCreditService.getCurrentCondition(dto);
        AllConditionVO conTitle = studentCreditService.getConditionTitle(dto);

        ServerResult serverResult = new ServerResult(0, "成功", new Object[] {conTitle, list});
        return serverResult;
    }
}
