package com.atguigu.springbootcache.mapper;

import com.atguigu.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Select;

/**
 * Created by JHW on 2021/8/29.
 */

// mybatis
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}
