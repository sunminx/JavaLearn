package com.xy.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private List<T> list;
    private int cap;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public BlockedQueue() {
        this(DEFAULT_CAPACITY);
    }

    public BlockedQueue(int cap) {
        this.cap = cap;
        this.list = new ArrayList<>();
    }

    public void add() {
        lock.lock();
        try {
            while (list.size() == cap) {
                condition.await();
            }
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T remove() {
        lock.lock();
        try {
            while (list.isEmpty()) {
                condition.await();
            }
            condition.signalAll();
            return list.remove(list.size() - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
