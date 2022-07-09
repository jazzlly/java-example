package com.java.examples.basic.enumdemo;

import lombok.Getter;
import lombok.ToString;

/**
 * 枚举类适用于创建有限的，固定的实列
 * 比如服务端返回的错误编码
 *
 * 常用方法
 */
@Getter
@ToString
public enum EnumSeason1 {

    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    public static void main(String[] args) {
        for (EnumSeason1 e: EnumSeason1.values()) {
            System.out.println(e);
        }

        System.out.println("-----------------------");
        System.out.println(EnumSeason1.valueOf("AUTUMN"));
    }

}
