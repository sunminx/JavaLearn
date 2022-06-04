package com.xy.multithread;

import java.util.concurrent.CountDownLatch;

public class VolatileDemo1 implements Runnable {

    private boolean flag = true;

    public void run() {
        System.out.println("method t start ...");
        while (flag) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method t loop ...");
        }
        System.out.println("method t end ...");
    }

    public void setFlag() {
        flag = !flag;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo1 demo1 = new VolatileDemo1();

        new Thread(demo1, "t1").start();

        Thread.sleep(10);
         demo1.setFlag();

        Thread.sleep(10000);
    }
}
