package com.xy.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * synchronized/ReentrantLock 这两种锁，无论是读线程还是写线程 只要获取锁都是独占的。
 * 在释放锁之前，其他线程都是阻塞等待获取锁。
 *
 * 而实际场景下（读多写少），如果当前时刻所有的获取锁线程都是读线程，没有线程尝试修改共享变量
 * 此时，是可以运行多个线程是并行运行的，运行多个读线程都获取到锁，然后并行执行。
 */
public class ReadWriteLockDemo {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    private int cnt = 0;

    public int get() {
        readLock.lock();
        try {
            return cnt;
        } finally {
            readLock.unlock();
        }
    }

    public void add(int i) {
        writeLock.lock();
        try {
            cnt += i;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        int readThreadNum = 1000;
        int writeThreadNum = 5;

        List<Thread> readThreadList = new ArrayList<>(readThreadNum);
        for (int i = 0; i < readThreadNum; i++) {
            Thread readThread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +
                        " read " + readWriteLockDemo.get());
            }, "read thread " + i);
            readThreadList.add(readThread);
        }

        List<Thread> writeThreadList = new ArrayList<>(writeThreadNum);
        for (int j = 0; j < writeThreadNum; j++) {
            Thread writeThread = new Thread(()->{
                int val = (int) (Math.random()* 100);
                readWriteLockDemo.add(val);
                System.out.println(Thread.currentThread().getName() +
                        "write " + val);
            }, "write thread" + j);
            writeThreadList.add(writeThread);
        }

    }

}
