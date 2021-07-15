package com.mb.demo.model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class EmpMapperPageTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testPage() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            PageHelper.startPage(2, 5);
            List<Emp> emps = mapper.selectAllEmps();
            PageInfo<Emp> pageInfo = new PageInfo<>(emps);
            System.out.println(GsonUtils.toJson(pageInfo));
        }
    }
}