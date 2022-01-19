package com.atguigu.springbootdatamybatis.mapper;

import com.atguigu.springbootdatamybatis.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * Created by JHW on 2021/8/28.
 */

// 指定这是一个操作数据库的mapper     // 如果使用mybatis作为持久层框架,这么写就行
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    // 获取自增主建
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name)values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
