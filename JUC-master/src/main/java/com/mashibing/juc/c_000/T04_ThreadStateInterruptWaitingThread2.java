package com.mashibing.juc.c_000;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class T04_ThreadStateInterruptWaitingThread2 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[child thread] before lock~");
                try {
                    // lock.lock(); 无法被中断
                    lock.lockInterruptibly();
                    System.out.println("[child thread] isInterrupted: " + Thread.currentThread().isInterrupted());;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[child thread] after lock~");
            }
        });
        System.out.println("[main thread] got lock");
        lock.lock();

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
