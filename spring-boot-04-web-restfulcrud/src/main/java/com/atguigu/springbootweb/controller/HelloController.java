package com.atguigu.springbootweb.controller;

import com.atguigu.springbootweb.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by JHW on 2021/8/22.
 */

@Controller
public class HelloController {

//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";   //   举例 classpath:/templates/index.html
//    }


    /**
     * http://localhost:8084/crud/hello?user=aaa
     */
    @ResponseBody
    @RequestMapping("/hello")
    public  String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello World";
    }


    /**
     * 渲染视图后(会放在这个路径下: /templates)
     * classpath:/templates/success.html   举例
     */
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }
}
