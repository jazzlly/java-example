package com.java.examples.basic.enumdemo;

import lombok.Setter;
import lombok.ToString;

/**
 * 对于内容不变的，个数有限的常量实例，可以使用枚举来实现
 * 枚举在虚拟机内部就是一个受限制的类
 *
 * 枚举可以实现外部接口方法
 * 枚举的成员可以实现外部接口方法
 */
@Setter
@ToString
public class EnumSeasonClass {

    /** 可以作为枚举的成员变量 */
    private final String name;
    private final String desc;

    /** 枚举可以有构造函数, 但是在类的外部不能够通过构造函数创建枚举实例 */
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
