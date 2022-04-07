package com.xy.multithread;

import java.time.LocalTime;

/**
 * 守护线程：某种需要一直运行的线程 并且可以在其他工作线程全部退出后 主线程可以正常退出
 *
 * 如果我们定义一个一直运行的普通线程 并且没有负责线程在程序结束的时候将其中断退出。
 * 这将导致主线程无法退出
 * 主线程必须在所有的非守护线程全部退出以后才可以退出 此时程序才退出运行。
 */
public class DaemonThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread timerThread = new Thread(()->{
            while (true) {
                System.out.println("now: " + LocalTime.now());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        // 注意设置守护线程必须在启动线程之前 否则将直接抛出中断异常
        // 将时间点线程设置为守护线程
        timerThread.setDaemon(true);

        // 启动时间点线程
        timerThread.start();


        // 5s后主线程退出
        // 如果没有将timerThread线程设置为守护线程 实际上这里主线程是无法退出的
        Thread.sleep(5000);
    }
}
