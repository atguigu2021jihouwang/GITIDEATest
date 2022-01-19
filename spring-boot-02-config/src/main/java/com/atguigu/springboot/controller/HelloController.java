package com.atguigu.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/21.
 */

@RestController
public class HelloController {

    /**
     * 如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；
     */
    @Value("${person.last-name}")
    private String name;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello "+name;
    }
}
