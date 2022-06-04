package com.xy.multithread.ask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContainerMonitor1 {
    volatile List<Integer> list = new ArrayList<>();

    public void add(int t) {
        list.add(t);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ContainerMonitor1 c1 = new ContainerMonitor1();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c1.add(i);
                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                if (c1.size() == 5) {
                    System.out.println("监测容量大小为5");
                    break;
                }
            }
        }).start();
    }
}
