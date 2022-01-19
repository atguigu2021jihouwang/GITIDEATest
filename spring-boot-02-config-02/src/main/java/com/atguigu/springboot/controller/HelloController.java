package com.atguigu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/21.
 */
@RestController
public class HelloController {

    /**
     *  /boot02/hello
     */
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
