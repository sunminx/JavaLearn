package com.xy.multithread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * semaphore另一个应用就是限流 限制同时访问资源的线程数
 *
 * 马士兵老师的教程中很好的例子：火车站买票的窗口；只有5个窗口 有非常多的人要买票，但是这个窗口的数量可以限制同时出票的人次
 * 这里卖票窗口起到限流的作用
 */
public class SemaphoreDemo2 implements Runnable{

    private Semaphore semaphore = new Semaphore(5, true);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.printf("thread %s is running\n", Thread.currentThread().getName());

            // 模拟任务需要处理1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo2 demo = new SemaphoreDemo2();
        ArrayList<Thread> threadList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(demo, "t-" + i));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        Thread.sleep(15000);
    }
}
