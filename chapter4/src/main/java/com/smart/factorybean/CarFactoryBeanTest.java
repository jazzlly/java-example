package com.smart.factorybean;

import com.smart.reflect.Car;
import com.smart.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1. FactoryBean是一个工厂类，同时也是一个Bean。
 * 2. 可以通过FactoryBeanc创建实际的Bean
 * 3. FactoryBean可以在创建Bean的过程中提供多样化的定制
 */
@Slf4j
public class CarFactoryBeanTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("carFactoryBean.xml");

        CarFactoryBean bean = context.getBean(CarFactoryBean.class);
        Car car = bean.getObject();
        log.info(GsonUtils.toJson(car));
        System.out.println(car.hashCode());

        System.out.println(bean.getObject().hashCode());
        System.out.println(bean.getObject().hashCode());
        System.out.println(bean.getObject().hashCode());

        /* 通过name获取factory bean时，返回的是factory bean中包含的对象
        <bean id="car" class="com.smart.factorybean.CarFactoryBean"
          p:brand="红旗CA72 Haha"
          p:color="Black"
          p:maxSpeed="250"
         */
        Car car1 = (Car) context.getBean("car");
        System.out.println(GsonUtils.toJson(car1));
        System.out.println(car1.hashCode());

        Car car2 = (Car) context.getBean("car");
        System.out.println(GsonUtils.toJson(car2));
        System.out.println(car2.hashCode());
    }
}
