package com.atguigu.springbootdatajdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by JHW on 2021/8/28.
 */
@Controller
public class HelloController {

    /**
     * jdbcTemplate 数据访问对象 springBoot 自带的,可以直接使用即可
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * http://localhost:8089/query
     */
    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM department");
        return list.get(0);
    }
}
