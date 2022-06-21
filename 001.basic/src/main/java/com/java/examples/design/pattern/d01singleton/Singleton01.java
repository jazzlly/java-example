package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 简单实现：
 * 1.静态私有实例
 * 2.在类加载时创建实例，虚拟机保证线程安全
 *
 * 好处：
 * 简单，能满足大部分使用场景
 *
 * 坏处：
 *  无懒加载
 *  可通过反射创建多个实例
 */
@Slf4j
public class Singleton01 {
    private static final Singleton01 instance = new Singleton01();

    private Singleton01() {
        log.info("create: {}", this.hashCode());
    }

    public static Singleton01 getInstance() {
        return instance;
    }

    public void method() {
        log.info("haha");
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        log.info("enter main ...");
        Singleton01 instance = Singleton01.getInstance();
        instance.method();

        Singleton01.reflection();
    }

    static void reflection() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class clazz = Singleton01.class;
        Constructor constructor = clazz.getDeclaredConstructor(new Class[]{});
        clazz.getConstructors();

        constructor.setAccessible(true);
        Singleton01 singleton01 =  (Singleton01) constructor.newInstance();
        singleton01.method();
    }
}
