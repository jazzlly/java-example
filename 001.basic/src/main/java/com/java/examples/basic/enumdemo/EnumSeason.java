package com.java.examples.basic.enumdemo;

import lombok.Getter;
import lombok.ToString;

/**
 * 枚举类适用于创建个数有限的，内容固定的，不变的实例
 * 比如服务端返回的错误编码
 */
@Getter
@ToString
public enum EnumSeason {

    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "烈日炎炎"),
    AUTUMN("秋天", "落叶纷纷"),
    WINTER("冬天", "冰天雪地");

    private final String name;
    private final String desc;

    EnumSeason(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static void main(String[] args) {
        System.out.println(EnumSeason.SPRING);
        System.out.println(EnumSeason.SUMMER);
        System.out.println(EnumSeason.WINTER);

        EnumSeason[] values = EnumSeason.values();
    }
}
