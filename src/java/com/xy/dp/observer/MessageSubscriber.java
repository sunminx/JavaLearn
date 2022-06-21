package com.xy.dp.observer;

public class MessageSubscriber implements Subscriber{
    @Override
    public void call() {
        System.out.println("[message] send message.");
    }
}
