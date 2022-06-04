package com.xy.multithread;

import java.util.concurrent.Semaphore;

/**
 * semaphore可以用作线程之间的同步
 */
public class SemaphoreDemo1 implements Runnable {

    private int count = 0;
    private Semaphore semaphore = new Semaphore(1, true);

    @Override
    public void run() {
        while (count < 100) {
            try {
                semaphore.acquire();
                System.out.printf("thread %s print %d\n", Thread.currentThread().getName(), count++);
//                Thread.sleep(10 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo1 demo = new SemaphoreDemo1();

        new Thread(demo, "t1").start();
        new Thread(demo, "t2").start();

        Thread.sleep(5000);
    }
}
