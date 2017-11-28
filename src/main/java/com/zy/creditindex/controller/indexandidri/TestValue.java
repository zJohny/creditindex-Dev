package com.zy.creditindex.controller.indexandidri;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${ZhaoYing}on 2017/11/24 0024
 */
@RestController
@Slf4j
public class TestValue {
    @Value("${value.commenStrig}")
    private String commenStrig;

    @RequestMapping("/value_common")
    public String valueIs(){
        log.info("value.commenStrig is {}"+commenStrig);
        return "success";
    }
}
