package com.leetcode.thread;


import java.util.concurrent.CountDownLatch;

public class L1114Print {

    CountDownLatch latchTwo = new CountDownLatch(1);
    CountDownLatch latchThree = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        latchTwo.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latchTwo.await();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latchThree.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        latchThree.await();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
