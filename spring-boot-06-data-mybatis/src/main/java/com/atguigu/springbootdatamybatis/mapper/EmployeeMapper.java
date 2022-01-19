package com.atguigu.springbootdatamybatis.mapper;

import com.atguigu.springbootdatamybatis.bean.Employee;


/**
 * Created by JHW on 2021/8/28.
 */

// 指定这是一个操作数据库的mapper
//@Mapper
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
