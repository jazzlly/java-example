package com.java.examples.design.pattern.d01singleton;

public enum SingletonEnum {
    INSTANCE;

    public void haha() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        instance.haha();
    }
}
