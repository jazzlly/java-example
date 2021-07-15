package com.smart.reflect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Car {
	private String brand;

	private String color;

	private int maxSpeed;

	public Car(){System.out.println("init car!!");}

	public void introduce() {
       System.out.println("brand:"+brand+";color:"+color+";maxSpeed:"+maxSpeed);
	}
}
