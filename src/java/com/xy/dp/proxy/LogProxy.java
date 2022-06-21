package com.xy.dp.proxy;

public class LogProxy extends Sender{

    public LogProxy(String message, String recvor, String date) {
        super(message, recvor, date);
    }

    public void send() {
        System.out.println("send params: " + super.toString());
        super.send();
    }
}
