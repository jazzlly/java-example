package com.java.examples.design.pattern.d01singleton.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 对于普通的单例模式，可以通过反射来破坏
 */
public class SingletonVoilation {
    private static SingletonVoilation instance = new SingletonVoilation();

    private SingletonVoilation() {
        System.out.println("constructor!");
    }

    public static SingletonVoilation getInstance() {
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        SingletonVoilation instance = SingletonVoilation.getInstance();
        Constructor<SingletonVoilation> constructor =
                SingletonVoilation.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        SingletonVoilation instance1 = constructor.newInstance();
        assertThat(instance).isNotEqualTo(instance1);
    }
}
