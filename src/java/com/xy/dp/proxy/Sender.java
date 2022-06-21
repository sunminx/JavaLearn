package com.xy.dp.proxy;

import java.util.concurrent.TimeUnit;

public class Sender {
    private String message;
    private String recvor;
    private String date;

    public Sender(String message, String recvor, String date) {
        this.message = message;
        this.recvor = recvor;
        this.date = date;
    }

    /**
     * 这里模拟发送请求 实际可能发送http或者rpc
     */
    public void send() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    public String toString() {
        return String.format("date=%s recv=%s message=%s", date, recvor, message);
    }
}
