package com.java.examples.jdbc;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by jiangrui on 6/12/15.
 */
public class PropertyDemo {
    public static void main(String[] args) throws IOException {
        PropertyDemo demo = new PropertyDemo();

        Properties properties = new Properties();
        properties.load(demo.getClass().getResourceAsStream("jdbc.properties"));

        String driver = properties.getProperty("jdbc.driverClassName");
        System.out.println("get property: " + driver);
    }
}
