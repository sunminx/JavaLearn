package com.xy.dp.observer;

public interface Subject {
    void register(Subscriber subscriber);

    void publish();
}
