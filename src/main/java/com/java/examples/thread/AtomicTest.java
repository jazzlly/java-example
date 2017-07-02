package com.java.examples.thread;


import java.util.concurrent.atomic.AtomicInteger;

class TestRunnable implements Runnable {

    private final AtomicInteger syncAtomic;

    public TestRunnable(AtomicInteger syncAtomic) {
        this.syncAtomic = syncAtomic;
    }

    @Override
    public void run() {
        final String threadName = Thread.currentThread().getName();

        for (int i = 0; i < 10; i++) {

            // If there is a sync in progress, we need not sync again
            if(!syncAtomic.compareAndSet(0, 1)) {
                System.out.println("There is a sync in progress..., thread: " + threadName + " sleep");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            System.out.println("current thread: " + threadName + " working...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("current thread: " + threadName + " working done!");
            syncAtomic.set(0);
        }

    }
}
/**
 * Created by jiangrui on 7/9/15.
 */
public class AtomicTest {


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger syncAtomic = new AtomicInteger(0);

        Thread thread1 = new Thread(new TestRunnable(syncAtomic));
        Thread thread2 = new Thread(new TestRunnable(syncAtomic));
        Thread thread3 = new Thread(new TestRunnable(syncAtomic));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
