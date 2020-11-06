package com.peter.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:peter
 * @Date:2020/11/6 16:41
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello demo";
    }

}
