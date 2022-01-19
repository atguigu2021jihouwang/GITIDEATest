package com.atguigu.springbootweb.controller;

import com.atguigu.springbootweb.dao.DepartmentDao;
import com.atguigu.springbootweb.dao.EmployeeDao;
import com.atguigu.springbootweb.entities.Department;
import com.atguigu.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by JHW on 2021/8/24.
 */
@Controller
public class EmployeeController {

    @Autowired
    public EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Map<String, Object> map){
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        map.put("emps",employees);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html  类路径: /templates/
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        System.out.println("保存的员工信息："+1);
        //来到添加页面,查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employee.setBirth(new Date());
        //来到员工列表页面
        System.out.println("保存的员工信息："+employee);
        //保存员工
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/updateEmp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/update";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/updateEmp")
    public String updateEmployee(Employee employee){
        employee.setBirth(new Date());
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
