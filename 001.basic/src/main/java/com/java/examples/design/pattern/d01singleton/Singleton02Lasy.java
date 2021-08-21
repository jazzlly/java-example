package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Singleton02Lasy {
    private static volatile Singleton02Lasy instance = null;

    private Singleton02Lasy() {
    }

    public static Singleton02Lasy getInstance() {
        if (instance == null) {
            synchronized (Singleton02Lasy.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton02Lasy();
                }
            }
        }
        return instance;
    }

    public void method() {
        log.info("haha");
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info(Singleton02Lasy.getInstance().hashCode() + "")).start();
        }

        Thread.sleep(5_000L);
    }
}
