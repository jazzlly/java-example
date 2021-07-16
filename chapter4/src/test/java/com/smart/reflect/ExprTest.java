package com.smart.reflect;

import com.smart.utils.GsonUtils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExprTest {

    @Test
    public void smoke() {
        // 属性的值可以在place holder中使用
        System.setProperty("foo_env", "test");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:expr_${foo_env}.xml");
        Car bean = (Car) context.getBean("car");
        System.out.println(GsonUtils.toJson(bean));
    }
}
