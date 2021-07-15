package com.mb.demo.mapper;

import com.mb.demo.model.Dept;
import com.mb.demo.model.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmpMapper接口绑定到EmpMapper.xml文件, 通过xml描述sql
 *
 * 参数映射： 优先使用 注解 @Param > [param1, param2, ] >>> [arg0, arg1, ...]
 */
public interface DeptMapper {

    Dept selectDept(Long deptId);

    /** 获取部门和部门中所有员工 */
    Dept selectDeptAndEmps(Long deptId);

    Integer insertDept(@Param("deptName") String deptName);
}
