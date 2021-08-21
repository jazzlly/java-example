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
public enum EnumSeason2 implements TestInterface {

    SPRING {
        @Override
        public void show() {
            System.out.println("春天在哪里呀~");
        }
    },
    SUMMER,
    AUTUMN,
    WINTER {
        @Override
        public void show() {
            System.out.println("Winter is comming!");
        }
    };

    public static void main(String[] args) {
        for (EnumSeason2 e: EnumSeason2.values()) {
            System.out.println(e);
            e.show();
        }
    }

    @Override
    public void show() {
        System.out.println("wahahaha!");
    }
}
