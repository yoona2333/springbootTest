package com.example.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author Administrator
 * @since 2026-11-06 上午 11:29
 */
@ControllerAdvice("com.example.controller") //指定要扫描异常的包
public class GlobalExceptionHandler {

    //引入hutool工具类的log类，用于记录异常信息
   private  static final Log log= LogFactory.get();

    //**捕获 Controller 抛出的 `Exception` 类型以及它所有子类异常**，做统一返回、统一异常响应，避免直接返回难看的堆栈给前端
    @ExceptionHandler(Exception.class)
    //返回 JSON 格式的异常信息
    @ResponseBody
    public Result error(Exception e){
        log.error("异常信息",e);//前端拿到的是 `{"code":500,"msg":"服务器内部异常","data":null}`，**不会看到 Java 报错堆栈**，避免信息泄露
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    //返回 JSON 格式的异常信息
    @ResponseBody
    public Result error(CustomException e){
        log.error("异常信息",e);//前端拿到的是 `{"code":500,"msg":"服务器内部异常","data":null}`，**不会看到 Java 报错堆栈**，避免信息泄露
        return Result.error(e.getCode(),e.getMsg());
    }



}
