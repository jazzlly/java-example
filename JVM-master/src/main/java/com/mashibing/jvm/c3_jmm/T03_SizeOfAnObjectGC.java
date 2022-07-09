package com.mashibing.jvm.c3_jmm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class T03_SizeOfAnObjectGC {
    // volatile会防止JIT的dead code elimination
    static volatile Object consumer;

    // instance指向的对象不会回收
    // consumer指向的会被GC
    // 发生minor GC时， instance对象会在survivor区域不停的移动. 对象头区域会不停变化
    public static void main(String[] args) {
        Object instance = new Object();
        long lastAddr = VM.current().addressOf(instance);
        ClassLayout layout = ClassLayout.parseInstance(instance);
        System.out.println(layout.toPrintable());
        System.out.println("hash code: " + instance.hashCode());

        for (int i = 0; i < 10_000; i++) {
            long currentAddr = VM.current().addressOf(instance);
            if (currentAddr != lastAddr) {
                System.out.println(layout.toPrintable());
                System.out.println("hash code: " + instance.hashCode());
            }

            for (int j = 0; j < 10_000; j++) {
                consumer = new Object();
            }
            lastAddr = currentAddr;
        }
    }

}