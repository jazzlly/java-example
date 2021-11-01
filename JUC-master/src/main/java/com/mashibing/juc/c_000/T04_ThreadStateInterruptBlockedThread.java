package com.mashibing.juc.c_000;

import java.util.concurrent.locks.LockSupport;

public class T04_ThreadStateInterruptBlockedThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before block~");
                synchronized (T04_ThreadStateInterruptBlockedThread.class) {
                    System.out.println("enter block");
                }
                System.out.println("after block~");
            }
        });

        System.out.println("thread start");
        synchronized (T04_ThreadStateInterruptBlockedThread.class) {
            thread.start();

            System.out.println("before main thread sleep~");
            Thread.sleep(1000);
            System.out.println("thread state: " + thread.getState());

            System.out.println("thread interrupt");
            thread.interrupt();

            Thread.sleep(5_000);
            System.out.println("thread state after interrupt: " + thread.getState());
        }

        System.out.println("thread join");
        thread.join();
        // LockSupport.unpark(thread);
    }
}
