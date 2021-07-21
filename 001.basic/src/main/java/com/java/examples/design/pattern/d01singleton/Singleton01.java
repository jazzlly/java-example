package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Singleton01 {
    private static Singleton01 instance = new Singleton01();

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return instance;
    }

    public void method() {
        log.info("haha");
    }

    public static void main(String[] args) {
        Singleton01 instance = Singleton01.getInstance();
        instance.method();
    }
}
