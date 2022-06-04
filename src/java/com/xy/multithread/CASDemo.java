package com.xy.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {
    private static int count = 0;

    /**
     * version 0: 没有对count++进行并发同步操作 出现并发修改错误
     */
    /*public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }*/

    /**
     * version 1: 使用synchronized对方法进行加锁
     */
    /*public synchronized static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }*/

    /**
     * version 2: 使用ReentrantLock仅对count++语句加锁 缩小加锁范围
     */
    /*private static ReentrantLock lock = new ReentrantLock();

    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }*/

    /**
     * version 4: 使用自定义的使用synchronized关键字实现的CAS对count进行并发安全的修改
     */
    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);

        int expect;
        while (!compareAndSwapCount((expect = getCount()), expect + 1)) {}
    }

    private synchronized static boolean compareAndSwapCount(int expectCount, int newCount) {
        if (expectCount == count) {
            count = newCount;
            return true;
        }
        return false;
    }

    private static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        int threadNum = 100;
        CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        try {
                            request();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });

            t.start();
        }

        latch.await();

        long end = System.currentTimeMillis();
        System.out.printf("timeCost: %d, count: %d\n", (end - start), count);
    }
}
