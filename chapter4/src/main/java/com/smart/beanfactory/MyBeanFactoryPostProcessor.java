package com.smart.beanfactory;

import com.smart.reflect.Car;
import com.smart.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {
		BeanDefinition bd = bf.getBeanDefinition("car");
		bd.getPropertyValues().addPropertyValue("brand", "11 奇瑞QQ(Mod by BFPP)");
		bd.getPropertyValues().addPropertyValue("color", "huhu");

		// 设置初始化方法
		bd.setInitMethodName("introduce");
		// 设置bean的scope
		bd.setScope(BeanDefinition.SCOPE_PROTOTYPE); // 可以设置bean的scope

		log.info("调用MyBeanFactoryPostProcessor.postProcessBeanFactory()！");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beanFactoryPostDemo.xml");

		Car car = context.getBean(Car.class);
		log.info("bean created: {}", GsonUtils.toJson(car));
		log.info("car hashcode: {}", car.hashCode());

		Car car1 = context.getBean(Car.class);
		log.info("ca1 hashcode: {}", car1.hashCode());

		// assertThat(car.hashCode()).isNotEqualTo(car1.hashCode());
		assertThat(car == car1).isFalse();
	}

}
