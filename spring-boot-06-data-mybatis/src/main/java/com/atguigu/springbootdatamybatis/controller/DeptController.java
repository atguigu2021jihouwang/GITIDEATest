package com.atguigu.springbootdatamybatis.controller;

import com.atguigu.springbootdatamybatis.bean.Department;
import com.atguigu.springbootdatamybatis.bean.Employee;
import com.atguigu.springbootdatamybatis.mapper.DepartmentMapper;
import com.atguigu.springbootdatamybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/28.
 */

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     *  http://localhost:8087/dept/1
     */
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    /**
     *  http://localhost:8087/dept?departmentName=AA   新增
     */
    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }

    /**
     * http://localhost:8087/emp/1
     */
    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

}
