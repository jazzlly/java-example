package com.mb.demo.mapper;

import com.mb.demo.model.MdmOrg;
import com.mb.demo.model.MdmOrgExample;
import com.mb.demo.utils.GsonUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MdmOrgMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testCreate() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MdmOrgMapper mapper = session.getMapper(MdmOrgMapper.class);
            String id = UUID.randomUUID().toString().replace("-", "");
            int ret = mapper.insert(MdmOrg.builder()
                    .id(id)
                    .name("test org")
                    .abbreviation("test org")
                    .description("hahaha")
                    .orgCodeReal(id)
                    .opTenantId(1)
                    .createTime(new Date(System.currentTimeMillis()))
                    .build());
            System.out.println(ret);

            session.commit();
            MdmOrg mdmOrg = mapper.selectByPrimaryKey(id);
            System.out.println(GsonUtils.toJson(mdmOrg));
        }
    }

    @Test
    public void testSelectByExample() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MdmOrgMapper mapper = session.getMapper(MdmOrgMapper.class);
            MdmOrgExample example = new MdmOrgExample();
            example.createCriteria().andNameLike("%org%")
                    .andOpTenantIdEqualTo(1);

            List<MdmOrg> mdmOrgs = mapper.selectByExample(example);
            System.out.println(GsonUtils.toJson(mdmOrgs));
        }
    }
}