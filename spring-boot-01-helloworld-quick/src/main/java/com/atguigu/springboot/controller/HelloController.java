package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/20.
 */
//@ResponseBody  // 将所有数据以json形式的数据返回出去
//@Controller
@RestController  // 可以将以上2个注解换成这一个注解,意思是一样的。
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick!";
    }
}
