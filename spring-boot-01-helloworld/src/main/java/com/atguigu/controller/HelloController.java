package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JHW on 2021/8/19.
 */

@Controller
public class HelloController {

         @ResponseBody
         @RequestMapping("/hello")
         public String hello(){
             return "Hello World!";
         }

}
