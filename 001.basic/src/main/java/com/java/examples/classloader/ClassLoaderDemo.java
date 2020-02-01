package com.java.examples.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ClassLoaderDemo {

    // https://www.cnblogs.com/mq0036/p/8566427.html
    @Test
    public void loadClassFile() {
        // 从URLClassLoader类中获取类所在文件夹的方法
        // 对于jar文件，可以理解为一个存放class文件的文件夹
        try {
            Method method = URLClassLoader.class.getDeclaredMethod(
                    "addURL", URL.class);
            method.setAccessible(true);     // 设置方法的访问权限

            // 获取系统类加载器
            Path path = Paths.get(System.getProperty("user.dir"), "Bar.class");
            File file = path.toFile();
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = file.toURI().toURL();
            try {
                method.invoke(classLoader, url);
                log.debug("读取文件[name={}]", file.getName());
            } catch (Exception e) {
                log.error("读取文件[name={}]失败", file.getName());
            }

            Class<?> fooClass = Class.forName("Bar");
            Object foo = fooClass.newInstance();
            Method msgMethod = fooClass.getDeclaredMethod("msg", String.class);
            msgMethod.setAccessible(true);
            msgMethod.invoke(foo, "wahaha...");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadJarFile() {
        // 从URLClassLoader类中获取类所在文件夹的方法
        // 对于jar文件，可以理解为一个存放class文件的文件夹
        try {
            Method method = URLClassLoader.class.getDeclaredMethod(
                    "addURL", URL.class);
            method.setAccessible(true);     // 设置方法的访问权限

            // 获取系统类加载器
            Path path = Paths.get(System.getProperty("user.dir"), "Foo.jar");
            File file = path.toFile();
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = file.toURI().toURL();
            try {
                method.invoke(classLoader, url);
                log.debug("读取文件[name={}]", file.getName());
            } catch (Exception e) {
                log.error("读取文件[name={}]失败", file.getName());
            }

            Class<?> fooClass = Class.forName("Foo");
            Object foo = fooClass.newInstance();
            Method msgMethod = fooClass.getDeclaredMethod("msg", String.class);
            msgMethod.setAccessible(true);
            msgMethod.invoke(foo, "wahaha...");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
