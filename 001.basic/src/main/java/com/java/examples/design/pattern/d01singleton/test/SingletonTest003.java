package com.java.examples.design.pattern.d01singleton.test;

public class SingletonTest003 {

    private SingletonTest003() {
        System.out.println("create singleton 2");
    }

    private static class Holder {
        private static final SingletonTest003 instance = new SingletonTest003();
    }

    public static SingletonTest003 getInstance() {
        return Holder.instance;
    }

    public void haha() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        System.out.println("main begin ...");
        SingletonTest003 instance = SingletonTest003.getInstance();
        instance.haha();
    }
}
