package com.tedu.shangshake.controller;

import com.tedu.shangshake.pojo.KindDAO;
import com.tedu.shangshake.pojo.ServerResult;
import com.tedu.shangshake.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class KindController {
    @Autowired
    KindService kindService;

    @RequestMapping("/kind/selectAllKind")
    public ServerResult selectAllKind(){
        List<KindDAO> list = kindService.selectAllKind();
        ServerResult serverResult = new ServerResult(0,"成功",list);
        return serverResult;
    }
}
