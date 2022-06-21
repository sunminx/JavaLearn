package com.xy.dp.observer;

public class PhoneSubscriber implements Subscriber{
    @Override
    public void call() {
        System.out.println("[phone] call phone.");
    }
}
