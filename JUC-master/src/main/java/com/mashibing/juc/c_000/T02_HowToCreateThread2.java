package com.mashibing.juc.c_000;

import java.util.concurrent.*;

public class T02_HowToCreateThread2 {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            return "wuhu!";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<String> future = threadPool.submit(new MyCallable());
        System.out.println(future.get());
        threadPool.shutdown();

        System.out.println("haha");
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.join();
        System.out.println(futureTask.get());
    }

}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
