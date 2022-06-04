package com.xy.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch可以用作线程同步 一般是主线程等待所有任务线程的完成
 * 主线程和子线程之前的同步
 */
public class CountDownLatchDemo implements Runnable {

    private CountDownLatch latch = new CountDownLatch(10);
    private List<Integer> nums = new ArrayList<>(1000000);
    private AtomicInteger sum = new AtomicInteger(0);

    {
        for (int i = 0; i < 1000000; i++) {
            nums.add(1);
        }
    }

    @Override
    public void run() {
        int idx = Integer.parseInt(Thread.currentThread().getName());
        int start = idx * 100000;
        int end = (idx + 1) * 100000;

        for (int i = start; i < end; i++) {
            sum.addAndGet(nums.get(i));
        }
        latch.countDown();
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo demo = new CountDownLatchDemo();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(demo, i+""));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        // 此时主线程等待全部计算进程完成才会继续往下执行
        demo.await();
        System.out.println(demo.sum);
    }
}
