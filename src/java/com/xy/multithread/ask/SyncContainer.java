package com.xy.multithread.ask;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SyncContainer<T> {
    private static final int MAX = 10;

    private LinkedList<T> lists;

    public SyncContainer() {
        this.lists = new LinkedList<>();
    }

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        notifyAll();
    }

    public synchronized T get() {
        while (lists.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = lists.removeFirst();
        notifyAll();
        return t;
    }

    public static void main(String[] args) {
        SyncContainer<Integer> container = new SyncContainer();

        for (int i = 0; i < 2; i++) {
            final int v = i;
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    container.put(v * j);
                }
            }, "p-"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while (true) {
                    Integer v = container.get();
                    System.out.println("获取到值: " + v);
                }
            }, "c-"+i).start();
        }
    }

}
