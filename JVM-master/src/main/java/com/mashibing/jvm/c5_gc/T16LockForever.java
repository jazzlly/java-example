package com.mashibing.jvm.c5_gc;

public class T16LockForever {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (T16LockForever.class) {
                    while (true) {
                        System.out.println("hahah");
                        try {
                            Thread.sleep(10_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t.setName("locking forever!");

        t.start();
        System.out.println("t1 start!");
        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (T16LockForever.class) {
                    System.out.println("Never get here");
                }
            }
        });
        t2.setName("waiting forerver!");
        t2.start();
        System.out.println("t2 start!");

        t.join();
        t2.join();
    }
}
