package com.xy.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent.Atomic包内提供了一些线程安全的基础数据类型
 * 使用CAS的 无锁的方式 实现并行读写的线程安全
 *
 * 只有确定当前值等于预期值的时候，才会写入。
 */
public class AtomicDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);

        final int times = 100000;

        Thread addThread = new Thread(()->{
            for (int i = times; i > 0; i--) {
                count.addAndGet(i);
            }
        });

        Thread decThread = new Thread(()->{
            for (int i = -times; i < 0; i++) {
                count.addAndGet(i);
            }
        });

        addThread.start();
        decThread.start();

        addThread.join();
        decThread.join();

        System.out.println("end; count = " + count);
    }
}
