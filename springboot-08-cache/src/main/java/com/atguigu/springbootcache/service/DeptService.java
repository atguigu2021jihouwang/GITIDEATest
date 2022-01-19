package com.atguigu.springbootcache.service;

import com.atguigu.springbootcache.bean.Department;
import com.atguigu.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * Created by JHW on 2021/8/29.
 */
//@CacheConfig(cacheNames="dept",cacheManager = "deptCacheManager")   // 通过注解的方式操作缓存
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    // 需要将缓存对象注册进来
    @Qualifier("deptCacheManager")  // 通过 @Qualifier 指定名字
    @Autowired
    RedisCacheManager deptCacheManager;

//    @Cacheable(/*cacheNames = "dept"*/)
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门"+id);
//        Department department = departmentMapper.getDeptById(id);
//        return  department;
//    }

    // 通过代码获取缓存,对缓存进行crud操作
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);

        // 获取缓存对象
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1",department);  // 对缓存对象进行crud
        return  department;
    }

}
