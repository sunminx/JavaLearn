package com.xy.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition是ReentrantLock锁的线程协调工具
 */
public class ConditionDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    private static int cnt = 0;

    private void add(int i) {
        lock.lock();
        try {
            cnt += i;
            // 在结束运行 释放lock锁之前 通过condition工具主动唤醒其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private int get() throws InterruptedException {
        lock.lock();
        try {
            while (cnt % 2 != 0) {
                // 如果此时cnt不是偶数 通过condition工具主动阻塞当前线程 并释放lock锁
                condition.await();
            }
            return cnt;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
