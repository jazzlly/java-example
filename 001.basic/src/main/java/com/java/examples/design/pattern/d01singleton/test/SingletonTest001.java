package com.java.examples.design.pattern.d01singleton.test;

public class SingletonTest001 {
    private static final SingletonTest001 instance = new SingletonTest001();

    private SingletonTest001() {
        System.out.println("create singleton 01");
    }

    public static SingletonTest001 getInstance() {
        return instance;
    }

    public void haha() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        System.out.println("main start ...");
        SingletonTest001 instance = SingletonTest001.getInstance();
        instance.haha();
    }
}
