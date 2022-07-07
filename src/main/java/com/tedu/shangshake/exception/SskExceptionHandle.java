package com.tedu.shangshake.exception;

import com.tedu.shangshake.pojo.ServerResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SskExceptionHandle {

    @ExceptionHandler
    public ServerResult exceptionHandle(Throwable e){
        String message = e.getMessage();

        e.printStackTrace();
        ServerResult serverResult = new ServerResult(500, "服务器出错，到控制台查看错误", message);
        return  serverResult;
    }
}
