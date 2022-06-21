package com.java.examples.design.pattern.d01singleton.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Slf4j
public class SingletonTest003 {
    private SingletonTest003() {
        log.info("created: {}", this.hashCode());
    }

    public static SingletonTest003 getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final SingletonTest003 instance = new SingletonTest003();
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        SingletonTest003 instance = SingletonTest003.getInstance();
        reflect();
    }

    static void reflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        Constructor<SingletonTest003> constructor =
                SingletonTest003.class.getDeclaredConstructor(new Class[]{});
        constructor.setAccessible(true);
        SingletonTest003 instance = constructor.newInstance();
    }
}
