package com.demo.mybatis.demo.mapper;

import com.demo.mybatis.demo.model.MdmOrg;
import com.demo.mybatis.demo.model.MdmOrgExample;
import com.demo.mybatis.demo.utils.GsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MdmOrgMapperTest {

    @Autowired
    MdmOrgMapper mdmOrgMapper;

    @Test
    void smoke() {
        String id = UUID.randomUUID().toString().replace("-", "");
        int ret = mdmOrgMapper.insert(MdmOrg.builder()
                .id(id)
                .name("test org xxxx")
                .abbreviation("test org")
                .description("hahaha")
                .orgCodeReal(id)
                .opTenantId(2)
                .createTime(new Date(System.currentTimeMillis()))
                .build());
        System.out.println(ret);

        MdmOrgExample example = new MdmOrgExample();
        example.createCriteria().andNameLike("%xxx%")
                .andOpTenantIdEqualTo(2);
        List<MdmOrg> mdmOrgs = mdmOrgMapper.selectByExample(example);
        System.out.println(GsonUtils.toJson(mdmOrgs));
    }
}