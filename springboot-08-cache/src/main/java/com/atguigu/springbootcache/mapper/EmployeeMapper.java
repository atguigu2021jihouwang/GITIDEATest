package com.atguigu.springbootcache.mapper;

import com.atguigu.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by JHW on 2021/8/29.
 */

// mybatis
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);

}
