package com.atguigu.springbootcache.controller;

import com.atguigu.springbootcache.bean.Employee;
import com.atguigu.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JHW on 2021/8/29.
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 查询一个员工
     * http://localhost:8082/emp/2
     */
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    /**
     * 更新一名员工
     * http://localhost:8082/emp?id=1&lastName=张三&email=zhangsan@atguigu.com&gender=0&dId=1
     */
    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    /**
     * 删除一名员工
     * http://localhost:8082/delemp?id=2
     */
    @GetMapping("/delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    /**
     * 根据员工姓名查询
     * http://localhost:8082/emp/lastname/lisi
     */
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
