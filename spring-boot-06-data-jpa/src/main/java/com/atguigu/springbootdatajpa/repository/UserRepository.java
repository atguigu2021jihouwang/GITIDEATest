package com.atguigu.springbootdatajpa.repository;

import com.atguigu.springbootdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JHW on 2021/8/29.
 */
//继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer>{
}
