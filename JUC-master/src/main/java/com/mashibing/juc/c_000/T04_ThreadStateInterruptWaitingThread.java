package com.mashibing.juc.c_000;

import java.util.concurrent.locks.LockSupport;

public class T04_ThreadStateInterruptWaitingThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[child thread] before park~");
                try {
                    LockSupport.park();
                    System.out.println("[child thread] isInterrupted: " + Thread.currentThread().isInterrupted());;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("[child thread] after park~");
            }
        });
        System.out.println("[main thread] start child thread");
        thread.start();

        System.out.println("[main thread] before main thread sleep~");
        Thread.sleep(1000);

        System.out.println("[main thread] interrupt child");
        thread.interrupt();
        Thread.sleep(3_000);


        System.out.println("[main thread] join child");
        thread.join();
        // LockSupport.unpark(thread);
    }
}
