package com.atguigu.springbootcache.controller;

import com.atguigu.springbootcache.bean.Department;
import com.atguigu.springbootcache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/29.
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * http://localhost:8082/dept/1
     */
    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDeptById(id);
    }
}
