package com.mashibing.juc.c_028_FalseSharing;

public class RyanCacheLineDemo0 {

    static class Data {
        public volatile long[] array = new long[8];
        public volatile long d = 0L;
    }

    static Data[] data = new Data[2];

    static {
        data[0] = new Data();
        data[1] = new Data();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 1_000_000_00L; i++) {
                    data[0].d++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < 1_000_000_00L; i++) {
                    data[1].d++;
                }
            }
        });

        long begin = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - begin);
    }
}
