package com.xy.multithread;

import java.util.List;

/**
 * 线程之间通知
 * 一个获取到锁，在执行过程中 由于继续运行条件不满足 可以释放锁，通知其他线程运行
 *
 * wait/notify 的前提是获取到锁，在临界区代码内。wait的执行会释放锁。
 *
 * 线程被唤醒 从wait()方法返回时会重新获取锁 此时继续往下执行之前 需要再次判断一下执行条件是否满足。
 * 因为线程可能因为意外被唤醒，被唤醒并不一定保证其执行条件得到满足。
 * 因此 wait() 不仅要在同步块或者同步方法内，还需要在一个while循环内。
 *
 * > right
 * while (tasks.isEmpty()) {
 *     this.wait();
 * }
 *
 * > wrong
 * if (tasks.isEmpty()) {
 *     this.wait();
 * }
 *
 * 线程通知机制是解决线程协调问题的关键。使得线程在临界区内可以选择暂停运行并释放锁。
 * 死锁也就是运行条件得不到满足 线程也不会释放已有的锁，而现在wait/notify给出了一个选择。
 */
public class ThreadNotifyDemo {

    private static int count = 0;

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(()->{
            String threadName = Thread.currentThread().getName();
            while (count < 100) {
                synchronized (lock) {
                    while (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(threadName + " output: " + count);
                    count++;
                    lock.notify();
                }
            }
        }, "odd");

        Thread evenThread = new Thread(()->{
            String threadName = Thread.currentThread().getName();
            while (count < 100) {
                synchronized (lock) {
                    while (count % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(threadName + " output: " + count);
                    count++;
                    lock.notify();
                }
            }
        }, "even");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();

        System.out.println("finished");
    }

    /**
     * 这个例子存在死锁问题
     *
     * 如果getTask()方法获取到锁,并且此时任务队列中没有任务，这将导致getTask()没有继续运行的条件（获取到一个任务，然后返回 方法退出）
     * 但是也没有释放锁，将导致另一个添加任务的方法一直无法获取到锁，无法往任务队列中添加任务。
     */
    static class TaskQueue {
        private List<Object> tasks;

        public synchronized void putTask(Object task) {
            tasks.add(task);
        }

        public synchronized Object getTask() {
            while (tasks.isEmpty()) {}
            return tasks.remove(tasks.size() - 1);
        }
    }
}
