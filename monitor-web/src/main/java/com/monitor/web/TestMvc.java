package com.monitor.web;

import com.monitor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zeusw on 2017/2/17.
 *
 * @author zeusw
 * @date 2017/02/17
 */
@RestController
@RequestMapping(value = "/test")
@ControllerAdvice

public class TestMvc {
    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping(value = "/test")
    public String ttMvc(){
       return testService.testTasks();
    }
}
