package com.smart.factorybean;

import com.smart.reflect.Car;
import com.smart.utils.GsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AbstractFactoryBean;

@Data
@Slf4j
public class CarFactoryBean extends AbstractFactoryBean<Car> {

    private String brand;

    private String color;

    private int maxSpeed;

    public Class<?> getObjectType() {
        return Car.class;
    }

    protected Car createInstance() throws Exception {
        log.info("create object in car factory");
        return Car.builder()
                .brand(this.brand)
                .color(this.color)
                .maxSpeed(this.maxSpeed)
                .build();
    }
}
