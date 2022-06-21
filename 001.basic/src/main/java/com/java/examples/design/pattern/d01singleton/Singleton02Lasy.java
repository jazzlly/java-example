package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒加载模式：
 * 1. 在调用getInstance方法时创建实例
 * 2. 静态instance变量需要通过volatile修饰
 * 3. getInstance方法需要通过双层判空，中间加synchronize类同步
 *
 * 好处:
 *  懒加载
 *  满足大部分场景
 *
 * 缺点：
 *  稍显繁琐
 *  可通过序列化或反射方式创建多个实例
 */
@Slf4j
public class Singleton02Lasy {
    private static volatile Singleton02Lasy instance = null;

    private Singleton02Lasy() {
        log.info("created: {}", this.hashCode());
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
    
    public static void main(String[] args) throws InterruptedException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info(Singleton02Lasy.getInstance().hashCode() + "")).start();
        }

        Thread.sleep(1_000L);
        reflection();
    }

    static void reflection() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        Constructor<Singleton02Lasy> constructor = Singleton02Lasy.class
                .getDeclaredConstructor(new Class[]{});
        constructor.setAccessible(true);
        Singleton02Lasy singleton02Lasy = constructor.newInstance();
        singleton02Lasy.method();
    }
}
