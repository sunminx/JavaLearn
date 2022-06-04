package com.xy.multithread.ask;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyncContainer2<T> {
    private static final int MAX = 10;
    private LinkedList<T> lists = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lists.add(t);
            producer.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        try {
            lock.lock();
            while (lists.isEmpty()) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = lists.removeFirst();
            producer.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
