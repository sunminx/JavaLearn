package com.xy.dp.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeClick implements Subject {

    private List<Subscriber> subscriberList;
    private int schedule;

    public TimeClick(int schedule) {
        this.subscriberList = new ArrayList<>();
        this.schedule = schedule;
    }

    public void register(Subscriber subscriber) {
        this.subscriberList.add(subscriber);
    }

    public void publish() {
        for (Subscriber subscriber : subscriberList) {
            subscriber.call();
        }
    }

    /**
     * 这里是定时会触发通知订阅者
     * 实际开发中可能是作为一个消息消费者 一旦读取到新到消息 将通知所有到订阅者
     * @throws InterruptedException
     */
    public void run() {
        for (;;) {
            new Thread(this::publish).start();
            try {
                TimeUnit.SECONDS.sleep(schedule);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
