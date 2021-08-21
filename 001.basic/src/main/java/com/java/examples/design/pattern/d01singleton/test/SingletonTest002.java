package com.java.examples.design.pattern.d01singleton.test;

public class SingletonTest002 {
    private static SingletonTest002 instance = null;

    SingletonTest002() {
    }

    public static SingletonTest002 getInstance() {
        if (instance == null) {
            synchronized (SingletonTest002.class) {
                if (instance == null) {
                    instance = new SingletonTest002();
                }
            }
        }
        return instance;
    }

    public void method() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        SingletonTest002 instance = SingletonTest002.getInstance();
    }
}
