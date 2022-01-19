package com.atguigu.springbootdatajpa.controller;

import com.atguigu.springbootdatajpa.entity.User;
import com.atguigu.springbootdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/29.
 */
@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;

    /**
     *  http://localhost:8085/user/1
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);  // jpa 自带的方法
        return user;
    }

    /**
     *  http://localhost:8085/user?lastName=zhangsan&email=aa@163.com
     */
    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }
}
