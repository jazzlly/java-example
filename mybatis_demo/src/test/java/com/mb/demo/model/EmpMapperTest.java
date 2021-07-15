package com.mb.demo.model;

import com.mb.demo.mapper.EmpMapper;
import com.mb.demo.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class EmpMapperTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testSelect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1L);
            System.out.println(emp.toString());
        }
    }

    @Test
    public void testSelectUsername() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp2(1L, "tututu");
            System.out.println(emp);
        }
    }

    @Test
    public void testSelectPojo() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp3(
                    Emp.builder().id(1L).username("tututu").build());
            System.out.println(emp);
        }
    }

    @Test
    public void testSelectPojo2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp4(
                    Emp.builder().id(1L).username("tututu").build(), 4);
            System.out.println(emp);
        }
    }

    @Test
    public void testSelectList() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmps(Arrays.asList("tututu", "wahaha"));
            System.out.println(emps);
        }
    }

    @Test
    public void testSelectArrayAnotation() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmps1(new String[]{"tututu", "wahaha"});
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testSelectIn() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmps2(new String[]{"tututu", "wahaha"});
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testSelectNameLike() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpNameLike("ha");
            System.out.println(GsonUtils.toJson(emps));

            System.out.println(GsonUtils.toJson(
                    mapper.selectEmpNameLike(null)));
        }
    }

    // 注意需要提交事务
    @Test
    public void testInsert() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp wuhuhu = Emp.builder()
                    .username("wakuku")
                    .deptId(3L)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .build();
            Integer emp1 = mapper.insertEmp(wuhuhu);
            System.out.println(emp1);
            System.out.println(wuhuhu);
            session.commit();   // 需要提交一下
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * 执行的sql还是一条一条的
     *                1384347 Query   INSERT INTO emp (username, dept_id, create_time)
     *             VALUES('wakuku', 3, '2021-07-07 08:47:49.682')
     *                 1384347 Query   INSERT INTO emp (username, dept_id, create_time)
     *             VALUES('wakuku', 3, '2021-07-07 08:47:49.683')
     *                 1384347 Query   INSERT INTO emp (username, dept_id, create_time)
     *             VALUES('wakuku', 3, '2021-07-07 08:47:49.683')
     */
    @Test
    public void testInsertBatchFake() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession(ExecutorType.BATCH);
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            for (int i = 0; i < 100; i++) {
                Emp wuhuhu = Emp.builder()
                        .username("wakuku")
                        .deptId(3L)
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .build();
                Integer emp1 = mapper.insertEmp(wuhuhu);
                // System.out.println(emp1);
                // System.out.println(wuhuhu);
            }
            session.commit();   // 需要提交一下
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    /**
     *  真正的批量插入
     *  INSERT INTO emp (username, dept_id, create_time) VALUES
     *             ('muhaha', 3, '2021-07-07 09:00:54.023')
     *             ('muhaha', 3, '2021-07-07 09:00:54.023')
     *             ('muhaha', 3, '2021-07-07 09:00:54.023')
     *             ('muhaha', 3, '2021-07-07 09:00:54.023')
     */
    @Test
    public void testInsertBatchReal() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession(ExecutorType.BATCH);
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            ArrayList<Emp> emps = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                emps.add(Emp.builder()
                        .username("muhaha")
                        .deptId(3L)
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .build());
            }

            Integer emp1 = mapper.insertBatch(emps);
            session.commit();   // 需要提交一下
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = sqlSessionFactory.openSession()){
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Integer tututu = mapper.updateEmp(Emp.builder()
                    .id(1L)
                    // .username("kukuku")
                    .deptId(2L)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .build());

            System.out.println(tututu);
            session.commit();   // 需要提交一下
        }
    }

    @Test
    public void testDelete() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Integer tututu = mapper.deleteEmp(Emp.builder()
                    .id(6L)
                    .build());
            System.out.println(tututu);
            session.commit();   // 需要提交一下
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    /** 联表查询 */
    @Test
    public void testSelectEmpDept() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmpDept(1L);
            System.out.println(GsonUtils.toJson(emp));
        }
    }

    /** 联表查询 */
    @Test
    public void testSelectEmpDept2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emps = mapper.selectEmpDept2(3L);
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    /** 联表查询 */
    @Test
    public void testSelectEmpInDept() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emp = mapper.selectEmpInDept(2L);
            System.out.println(GsonUtils.toJson(emp));
        }
    }

    @Test
    public void testSelectEmpInDept1() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emp = mapper.selectEmpInDept1(2L);
            System.out.println(GsonUtils.toJson(emp));
        }
    }

    @Test
    public void testSelectEmpAss() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmpAss(3L);
            System.out.println(GsonUtils.toJson(emp));
        }
    }

    @Test
    public void testDynamicSelect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpDynamic(null, null, 1L);
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testDynamicSelect2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpDynamic2(null, "wuhuhu", null);
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testDynamicSelect3() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpDynamic3(null, "wahaha", 2L);
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testSelectEmpByDeptName() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpByDeptName("测试");
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testSelectEmpInDept2() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpInDept2(Arrays.asList(1L, 3L));
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testSelectEmpInDept2_1() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpInDept2(new ArrayList<>());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testFilterDate() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = mapper.selectEmpFilterByTime("2020-10-1");
            System.out.println(GsonUtils.toJson(emps));
        }
    }
}