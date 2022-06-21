package com.xy.dp.observer;

public class EmailSubscriber implements Subscriber{
    @Override
    public void call() {
        System.out.println("[email] send email.");
    }
}
