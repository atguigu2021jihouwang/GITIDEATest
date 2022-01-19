package com.atguigu.springbootweb.controller;

import com.atguigu.springbootweb.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JHW on 2021/8/26.
 */
@ControllerAdvice
public class MyExceptionHandler {

      // json 不灵活
//    @ResponseBody
//    @ExceptionHandler({UserNotExistException.class})
//    public  Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message","用户出错啦");
//        return  map;
//    }

    // 视图,json 自适应    转发到/error进行自适应响应效果处理   如何定制错误的json数据
    @ExceptionHandler({UserNotExistException.class})
    public String handleException(Exception e, HttpServletRequest request){
         Map<String,Object> map = new HashMap<>();
        // 传入我们自己的错误状态码  4xx 5xx
        /**
         * Integer statusCode = (Integer) request
         *.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message","用户出错啦");

        // 将数据放在 request域中
        request.setAttribute("ext",map);

        return "forward:/error";
    }
}
