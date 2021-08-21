package com.java.examples.basic.enumdemo;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class EnumSeasonClass {

    private final String name;
    private final String desc;

    private EnumSeasonClass(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static final EnumSeasonClass SPRING =
            new EnumSeasonClass("春天", "春暖花开");
    public static final EnumSeasonClass SUMMER =
            new EnumSeasonClass("夏天", "烈日炎炎");
    public static final EnumSeasonClass AUTUMN =
            new EnumSeasonClass("秋天", "落叶纷纷");
    public static final EnumSeasonClass WINTER =
            new EnumSeasonClass("冬天", "冰天雪地");

    public static void main(String[] args) {
        System.out.println(EnumSeasonClass.SPRING);
        System.out.println(EnumSeasonClass.SUMMER);
    }
}
