package com.mb.demo.model;

import com.mb.demo.mapper.DeptMapper;
import com.mb.demo.mapper.EmpMapper;
import com.mb.demo.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
public class EmpMapperCacheTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 默认就开启了一级缓存
     *
     * 失效情况
     * 1. 默认作用域是基于sqlSession的, 不同sqlSession会失效
     * 2. 同一个sqlSession, 不同sql
     * 3. 同一个sqlSession, 执行了增删改操作
     * 4. 同一个sqlSession, 手动清理缓存
     * 5. 查询完就会保存到一级缓存
     */
    @Test
    public void testL1Cache() {
        // 第一次查询后， 后续查询全部命中缓存
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            for (int i = 0; i < 5; i++) {
                log.info("loop: {}", i);
                List<Emp> emps = mapper.selectEmpL1Cache(
                        Emp.builder().username("wahaha").build());
                System.out.println(GsonUtils.toJson(emps));
            }
        }
    }

    @Test
    public void testL1Cache1() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            // miss cache
            log.info("loop: {}", 1);
            List<Emp> emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            // miss cache
            log.info("loop: {}", 2);
            emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("muhahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            // got cache
            log.info("loop: {}", 3);
            emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    @Test
    public void testCacheCleanCache() {
        // 手动清空缓存
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            for (int i = 0; i < 5; i++) {
                log.info("loop: {}", i);
                List<Emp> emps = mapper.selectEmpL1Cache(
                        Emp.builder().username("wahaha").build());
                System.out.println(GsonUtils.toJson(emps));
                session.clearCache(); // 手动清空缓存
            }
        }
    }

    @Test
    public void testCacheDiffSessions() {
        // 不同session中的insert, update, delete 不会影响到其他session
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession();
             SqlSession sqlSession2 = sqlSessionFactory.openSession()) {
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);
            EmpMapper mapper2 = sqlSession2.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 1");
            List<Emp> emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            log.info("insert from session 2");
            mapper2.insertEmp(Emp.builder()
                    .username("wahaha")
                    .deptId(3000L)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .build());
            sqlSession2.commit();
            List<Emp> emps1 = mapper2.selectEmpNameLike("level");
            System.out.println(GsonUtils.toJson(emps1));

            // got cache
            log.info("select from session 1");
            emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    /**
     * 1. 默认开启了， 没有实现
     * 2. 作用域是应用级别
     * 3. 事务提交的时候保存(sqlSession关闭的时候)
     *
     * 失效：
     *  1. 同一个namespace进行了增删改
     *  2. xml sql语句中flushCache=false时，二级缓存不失效
     *  3. xml sql语句中可指定不存储到二级缓存
     *  4. 可通过cache-ref来共用不同mapper的缓存
     *      <!-- Dept公用Emp的缓存, 比如有联表查询 -->
     *     <cache-ref namespace="com.mb.demo.mapper.EmpMapper"/>
     */
    // sqlSession关闭后，保存二级缓存
    @Test
    public void testL2Session() {
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession()) {
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 1");
            List<Emp> emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }

        // 命中二级缓存
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession()) {
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 2");
            List<Emp> emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    // 事务提交后， 保存二级缓存
    @Test
    public void testL2Session2() {
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession()) {
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 1");
            List<Emp> emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            sqlSession1.commit(); // 事务提交后， 二级缓存保存

            log.info("select from session 2");
            emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            log.info("select from session 3");
            emps = mapper1.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    // xml sql中显示禁止二级缓存
    @Test
    public void testDisableL2Cache() {
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession()) {
            EmpMapper mapper1 = sqlSession1.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 1");
            List<Emp> emps = mapper1.selectEmpL2CacheDisable(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            sqlSession1.commit(); // 事务提交后， 二级缓存保存

            log.info("select from session 2");
            emps = mapper1.selectEmpL2CacheDisable(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }

    // dept和emp共用了一片缓存
    @Test
    public void testReuseCache() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            // miss cache
            log.info("select from session 1");
            List<Emp> emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            session.commit(); // 事务提交后， 二级缓存保存

            log.info("got from l2 cache!");
            emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));

            log.info("insert to other namespace ... ");
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            Integer ret = deptMapper.insertDept("测试2组");
            session.commit();

            log.info("select again after insert to other namespace ... ");
            emps = mapper.selectEmpL1Cache(
                    Emp.builder().username("wahaha").build());
            System.out.println(GsonUtils.toJson(emps));
        }
    }
}