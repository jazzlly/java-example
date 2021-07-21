package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Singleton03InnerStaticClass {

    private Singleton03InnerStaticClass() {
    }

    // 外部类加载时，内部类不会被加载
    private static class Holder {
        public static final Singleton03InnerStaticClass instance =
                new Singleton03InnerStaticClass();
    }

    // 实现lasy loading
    public static Singleton03InnerStaticClass getInstance() {
        return Holder.instance;
    }

    public void method() {
        log.info("haha");
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info(Singleton03InnerStaticClass.getInstance().hashCode() + "")).start();
        }
        Thread.sleep(5_000L);
    }
}
