package com.xy.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 是Java内部实现的可重入锁 （Synchronized是JVM利用操作系统实现的加锁操作）
 *
 * ReentrantLock 需要手动释放锁 释放锁操作在 finally块内，保证一定会释放锁
 * ReentrantLock 更加安全 可以 tryLock() 可以尝试立即获取锁或者尝试一定时间，获取不到将退出获取锁操作。
 * 相较于synchronized会一直阻塞到获取为止，ReentrantLock更加安全 tryLock()加锁操作可以避免发生死锁
 */
public class ReentrantLockDemo {

    static class Counter {
        private ReentrantLock lock = new ReentrantLock();

        private int count = 0;

        public void add(int i) {
            lock.lock();
            try {
                count += i;
            } finally {
                lock.unlock();
            }
        }
    }
}
