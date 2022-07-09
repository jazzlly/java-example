package com.java.examples.basic.enumdemo;

import org.junit.Test;

public class PersonTest {

    @Test
    public void smoke() {
        System.out.println(Person.builder()
                .name("wuyifan")
                .age(35)
                .sex(Person.Gender.FEMALE)
                .build());
    }

    @Test
    public void testSwitch() {
        Person.Gender gender = Person.Gender.FEMALE;
        switch (gender) {
            case MALE:
                System.out.println("帅哥");
                break;
            case FEMALE:
                System.out.println("美女");
                break;
            default:
                break;
        }
    }
}