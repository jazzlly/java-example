package com.java.examples.basic.enumdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public enum Gender {
        MALE, FEMALE;
    }

    private String name;

    private int age;

    private Gender sex;

    public static void smoke() {
        System.out.println(Person.builder()
                .name("wuyifan")
                .age(35)
                .sex(Person.Gender.FEMALE)
                .build());
    }

    public static void testSwitch() {
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

    public static void main(String[] args) {
        smoke();
        testSwitch();   
    }

}
