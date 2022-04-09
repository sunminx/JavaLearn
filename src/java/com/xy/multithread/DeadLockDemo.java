package com.xy.multithread;

/**
 * 死锁 多个线程都持有对方需要的锁，同时又等待获取对方持有的锁
 * 解决办法：多线程顺序获取锁
 */
public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                exec1();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                exec2();
            }
        });

        // 启动线程
        t1.start();
        t2.start();

        // 主线程等待子线程执行结束
        t1.join();
        t2.join();

        System.out.println("cnt1 = " + cnt1);
        System.out.println("cnt2 = " + cnt2);
    }

    private static int cnt1 = 0;
    private static int cnt2 = 0;

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    /**
     * 下面两个方法以不同的顺序来获取锁
     * 导致两个线程持有一个锁的同时，有需要等待对方持有的锁才能够继续执行
     * 导致线程等待状态将永远无法结束，允许可以看到JVM一直退出不了
     * 死锁发生之后只能强行结束进程
     * 因此要顺序获取锁
     */

    private static void exec1() {
        synchronized (lock1) {
            cnt1++;
            synchronized (lock2) {
                cnt2--;
            }
        }
    }

    private static void exec2() {
        // synchronized (lock1) {
        synchronized (lock2) {
            cnt2++;
            // synchronized (lock2) {
            synchronized (lock1) {
                cnt1--;
            }
        }
    }
}
