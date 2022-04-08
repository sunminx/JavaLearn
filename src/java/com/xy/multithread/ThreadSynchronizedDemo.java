package com.xy.multithread;

public class ThreadSynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        // test1
        // test1();

        // test2
        test2();
    }

    private static int count = 0;
    // 对共享变量的操作不进行同步
    // 运行后可以发现最终的结果并不等于0
    private static void test1() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                count--;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }

    private static void test2() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                synchronized (ThreadSynchronizedDemo.class) {
                    count++;
                }
            }
        });
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                synchronized (ThreadSynchronizedDemo.class) {
                    count--;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }
}
