package com.java.examples.reflection.classes;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class R100RetrievingClassObjectTest {
//    enum E { A, B };
//
//    static Object o = new Object() {
//        public void m() {}
//    };
//
//    public class A {};
//    class B {};
//
//    @Test
//    public void getClassFromObject() {
//        Class<?> c = "foo".getClass();
//        System.out.println("\"foo\".getClass(): " + c.toString());
//
//        Class<?> c1 = R100RetrievingClassObject.E.A.getClass();
//        System.out.println("E.A.getClass(): " + c1.toString());
//
//        byte[] bytes = new byte[1024];
//        Class<?> c2 = bytes.getClass();
//        System.out.println("(new byte[1024]).getClass(): " + c2.toString());
//
//        Set<String> s = new HashSet<String>();
//        Class<?> c3 = s.getClass();
//        System.out.println("(new HashSet<String>()).getClass(): " + c3.toString());
//    }
//
//    @Test
//    public void testGetClasses() {
//        System.out.println("------------------------------");
//        System.out.println("R100RetrievingClassObject.class.getClasses():");
//        Class<?>[] cs = R100RetrievingClassObject.class.getClasses();
//        for (Class<?> ci : cs) {
//            System.out.println(ci);
//        }
//        System.out.println("only get public declared class");
//        System.out.println("------------------------------");
//    }
//
//    @Test
//    public void testGetDeclaredClasses() {
//        System.out.println("------------------------------");
//        System.out.println("R100RetrievingClassObject().getClass().getDeclaredClasses(): ");
//        Class<?>[] cs = new R100RetrievingClassObject().getClass().getDeclaredClasses();
//        for(Class<?> ci : cs) {
//            System.out.println(ci);
//        }
//        System.out.println("get all declared classes");
//        System.out.println("------------------------------");
//    }
//
//    @Test
//    public void getDeclaringClass() throws NoSuchFieldException {
//        Field f = System.class.getField("out");
//        System.out.println("System.class.getField(\"out\").getDeclaringClass(): "
//                + f.getDeclaringClass());
//    }
//
//    @Test
//    public void getSuperClass() {
//        System.out.println("java.lang.String.class.getSuperclass(): " +
//                java.lang.String.class.getSuperclass());
//    }
//
//    @Test
//    public void getClassForName() throws ClassNotFoundException {
//        Class<?> c4 = Class.forName("com.java.examples.reflection.classes.R100RetrievingClassObject");
//        System.out.println("Class.forName(R100RetrievingClassObject): " + c4);
//    }
//
//    @Test
//    public void dotClassSyntax() {
//        System.out.println("String.class: " + String.class);
//        System.out.println("boolean.class: " + boolean.class);
//        System.out.println("Boolean.TYPE: " + Boolean.TYPE);
//        System.out.println("void.class: " + void.class);
//        System.out.println("Void.TYPE: " + Void.TYPE);
//        System.out.println("Double.class: " + Double.class);
//        System.out.println("java.io.PrintStream.class: " + java.io.PrintStream.class);
//        System.out.println("int [][][].class: " + int [][][].class);
//    }

}