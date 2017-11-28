package com.zy.creditindex.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${ZhaoYing}on 2017/10/27 0027
 * 全局捕获异常
 *
 */
@ControllerAdvice
public class SystemException {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Code","500");
        result.put("Msg","系统异常·····");
        return result;
    }
}

