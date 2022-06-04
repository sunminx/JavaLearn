package com.xy.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟CAS中的ABA问题
 *
 * 比较并决定是否替换: 之前读取的值和当前读到的值一致 -> 在此期间没有其他线程对共享变量进行修改
 * 上面的推论是不正确的，可能其他线程对共享变量进行了修改 然后又修改回来了
 * 这便是ABA问题
 */
public class CASABADemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger aInt = new AtomicInteger(1);

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(()->{
            int expectVal = aInt.get();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newVal = expectVal + 1;
            boolean isSet = aInt.compareAndSet(expectVal, newVal);
            System.out.println(Thread.currentThread().getName() + " set " + (isSet ? "success" : "fail"));
            latch.countDown();
        }, "main thread").start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aInt.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " aInt = " + aInt.get());

            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            aInt.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " aInt = " + aInt.get());
            latch.countDown();
        }, "sub thread").start();

        latch.await();
        System.out.println("bye ~");
    }
}
