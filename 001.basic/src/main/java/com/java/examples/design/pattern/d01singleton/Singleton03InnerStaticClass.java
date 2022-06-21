package com.java.examples.design.pattern.d01singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类方式
 * 私有的构造函数
 * 通过一个静态内部类的一个静态成员指向外部类的单例实例
 * 懒加载：外部类被加载时，不会加载内部类
 * 线程安全：虚拟机保证加载内部类时的线程安全
 *
 * 优点：
 * 懒加载，书写简单，满足大部分场景
 *
 * 缺点：
 * 可通过序列化或反射机制创建多个实例
 */
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
