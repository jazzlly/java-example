package com.demo.mybatis.demo.mapper;

import com.demo.mybatis.demo.model.TOrder;
import com.demo.mybatis.demo.model.TOrderExample;
import com.demo.mybatis.demo.utils.GsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("foo")
public class TOrderMapperTest {

    @Autowired
    TOrderMapper tOrderMapper;

    @Test
    void testInsert() {
        for (int i = 2000; i <2010 ; i++) {
            TOrder order = TOrder.builder().userId(4).orderId(i).build();
            tOrderMapper.insert(order);
        }
    }

    @Test
    void testSelect() {
        TOrderExample example = new TOrderExample();
        example.createCriteria().andOrderIdEqualTo(1003).andUserIdEqualTo(4);

        List<TOrder> tOrders = tOrderMapper.selectByExample(example);
        System.out.println(GsonUtils.toJson(tOrders));
    }
}