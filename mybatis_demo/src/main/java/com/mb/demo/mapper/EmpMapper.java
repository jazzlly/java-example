package com.mb.demo.mapper;

import com.mb.demo.model.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmpMapper接口绑定到EmpMapper.xml文件, 通过xml描述sql
 *
 * 参数映射： 优先使用 注解 @Param > [param1, param2, ] >>> [arg0, arg1, ...]
 */
public interface EmpMapper {
    Emp selectEmp(Long id);

    /**
     * 分步骤查询Emp和Dept
     * @param id
     * @return
     */
    Emp selectEmpAss(Long id);

    String selectEmpUsername(String username);

    Emp selectEmp2(@Param("id") Long id, @Param("username") String username);

    Emp selectEmp3(Emp emp);

    Emp selectEmp4(@Param("emp") Emp emp, @Param("dummy") int dummy);

    List<Emp> selectEmpDynamic(@Param("id") Long id,
                               @Param("username") String username,
                               @Param("deptId") Long deptId);

    List<Emp> selectEmpDynamic2(@Param("id") Long id,
                               @Param("username") String username,
                               @Param("deptId") Long deptId);

    List<Emp> selectEmpDynamic3(@Param("id") Long id,
                                @Param("username") String username,
                                @Param("deptId") Long deptId);

    List<Emp> selectEmpByDeptName(@Param("deptName") String deptName);

    List<Emp> selectEmpInDept2(@Param("deptIds") List<Long> deptIds);

    List<Emp> selectEmpFilterByTime(@Param("datetime") String datetime);

    List<Emp> selectEmpNameLike(@Param("username") String username);

    List<Emp> selectEmps(List<String> usernames);

    List<Emp> selectEmps1(@Param("usernames") String[] usernames);

    List<Emp> selectEmps2(@Param("usernames") String[] usernames);

    Integer insertEmp(Emp emp);

    Integer insertBatch(@Param("emps") List<Emp> emps);

    Integer updateEmp(Emp emp);

    Integer deleteEmp(Emp emp);

    /** 联表查询 */
    Emp selectEmpDept(Long id);

    Emp selectEmpDept2(Long id);

    /** 查找部门中所有用户 */
    List<Emp> selectEmpInDept(Long did);

    List<Emp> selectEmpInDept1(Long did);

    List<Emp> selectEmpL1Cache(Emp emp);

    List<Emp> selectEmpL2CacheDisable(Emp emp);

    List<Emp> selectAllEmps();
}
