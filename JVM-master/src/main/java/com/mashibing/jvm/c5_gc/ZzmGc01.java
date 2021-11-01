package com.mashibing.jvm.c5_gc;

public class ZzmGc01 {

    public static final int __1MB = 1024 * 1024;

    // -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    public static void main(String[] args) {
        byte[] bytes1 = new byte[__1MB * 2];
        byte[] bytes2 = new byte[__1MB * 2];
        byte[] bytes3 = new byte[__1MB * 2];
        byte[] bytes4 = new byte[__1MB * 4]; // 出现1次minorGC
    }
}
