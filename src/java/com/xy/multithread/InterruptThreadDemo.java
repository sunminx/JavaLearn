package com.xy.multithread;

import java.util.Scanner;

/**
 * 一个线程可以通过interrupt()中断或者设置中断变量的方式通知另一个线程应该退出执行
 * 注意这里是通知另一个线程 并不保证被通知线程立即退出
 * 需要看被通知线程的中断信号检查逻辑
 *
 * test1: interrupt()
 * test2: var
 * test3: 中断信号传递
 */
public class InterruptThreadDemo {

    public static void main(String[] args) {
        // test1
        // interruptTest1();

        // test2
        // interruptTest2();

        // test3
        interruptTest3();
    }

    /**
     * 通过 interrupt()调用来通知线程中断运行
     */
    private static void interruptTest1() {
        Thread subThread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()) {
                // System.out.println("sub thread running ...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 一旦主线程中断subThread 此时处于sleep状态的线程将抛出中断异常
                    // 并立即退出运行
                    e.printStackTrace();
                    break;
                }
            }

            System.out.println("sub thread finish ...");
        });

        subThread.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String tag = scanner.nextLine();
            if ("over".equalsIgnoreCase(tag)) {
                subThread.interrupt();
            }
        }
    }

    /**
     * 中断标志变量
     */
    static volatile boolean running = true;

    /**
     * 通过 中断标志变量来通知子线程可以中断运行
     */
    private static void interruptTest2() {
        Thread subThread = new Thread(()->{
            while (running) {
                System.out.println("sub thread running ...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            System.out.println("sub thread end .");
        });
        // 启动子线程
        subThread.start();

        try {
            // 主线程休眠3000ms 给子线程一些运行时间
            Thread.sleep(3000);
            // 主线程将中断标志变量置为false
            running = false;
            // 等待子线程执行结束
            subThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void interruptTest3() {
        Thread son = new Thread(()->{
            System.out.println("son thread start running.");
            Thread grandSon = new Thread(()->{
                System.out.println("grandson thread start running.");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("grandson thread running ...");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // e.printStackTrace();
                        // 这里中断异常需要立即退出循环
                        // 不然再次进入循环条件判断 会继续进入循环
                        break;
                    }
                }
                System.out.println("grandson thread end.");
            });

            // 子线程启动孙子线程
            grandSon.start();

            try {
                // 子线程等待孙子线程执行结束
                grandSon.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 子线程join()等待孙子线程中断异常 此时如果没有通知孙子线程中断退出
                // 将导致子线程退出 主线程退出 但是孙子线程没有退出 将一直执行下去 JVM也不会退出
                // 可以看到孙子线程的 "running ..." 一直在打印
                grandSon.interrupt();
            }

            System.out.println("son thread end.");
        });

        // 启动子线程
        son.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断子线程
        son.interrupt();

    }

}
