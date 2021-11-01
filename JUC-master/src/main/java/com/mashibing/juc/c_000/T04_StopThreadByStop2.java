package com.mashibing.juc.c_000;

import java.util.concurrent.locks.ReentrantLock;

public class T04_StopThreadByStop2 {


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[child thread] before sync~");
                synchronized (T04_StopThreadByStop2.class) {
                    System.out.println("[child thread] after sync~");
                }
            }
        });
        System.out.println("[main thread] before sync...");
        synchronized (T04_StopThreadByStop2.class) {
            System.out.println("[main thread] start child thread");
            thread.start();

            System.out.println("[main thread] before main thread sleep~");
            Thread.sleep(1000);

            System.out.println("[main thread] stop child");
            thread.stop();
            Thread.sleep(5_000);
            System.out.println("[main thread] join child");
        }

        thread.join();
        // LockSupport.unpark(thread);
    }
}
