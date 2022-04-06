package com.xy.multithread;

import java.util.concurrent.*;

public class ThreadCreateDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // test 1
        new EchoThread().start();

        // test 2
        new Thread(new EchoTask()).start();

        // test 3
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new PrintTask());
        System.out.println(future.get());
    }

    /**
     * 方式1：继承Thread类
     */
    static class EchoThread extends Thread {
        @Override
        public void run() {
            System.out.println("print by new thread 1");
        }
    }

    /**
     * 方法2：实现Runnable接口
     */
    static class EchoTask implements Runnable {

        @Override
        public void run() {
            System.out.println("print by new thread 2");
        }
    }

    /**
     * 方法3：实现Callable接口
     */
    static class PrintTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "return from new thread 3";
        }
    }
}
