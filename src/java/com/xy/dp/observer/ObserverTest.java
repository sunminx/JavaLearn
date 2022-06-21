package com.xy.dp.observer;

import java.util.concurrent.TimeUnit;

/**
 * 事件所有者这一侧保存全部的事件观察者(对事件感兴趣的)
 * 一旦事件触发 事件所有者将通知全部注册的观察者
 *
 * 事件所有者提供观察者注册和取消的接口
 */
public class ObserverTest {

    public static void main(String[] args) {
        TimeClick timeClick = new TimeClick(3);

        EmailSubscriber emailSubscriber = new EmailSubscriber();
        MessageSubscriber messageSubscriber = new MessageSubscriber();
        PhoneSubscriber phoneSubscriber = new PhoneSubscriber();

        /**
         *  一开始注册两个观察者
         */
        timeClick.register(emailSubscriber);
        timeClick.register(messageSubscriber);

        new Thread(timeClick::run).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 程序运行一段事件后 又注册一个观察者
         */
        timeClick.register(phoneSubscriber);
    }
}
