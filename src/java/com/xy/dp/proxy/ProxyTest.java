package com.xy.dp.proxy;

import java.util.Date;

public class ProxyTest {

    public static void main(String[] args) {
        /**
         * 如果被代理类全部直接继承自被代理类, 那么不能叠加被代理类实现全部代理功能 一次只能使用一次
         *
         * 如果代理类继承自另外的代理类 那么继承链实现了代理功能的叠加
         *
         * 只是这种代理功能的叠加顺序在 代理类的继承关系中定死了 在使用的时候无法进行自由组合
         *
         * 如果想要实现代理功能顺序的自由组合 那么使用组合而不是继承来实现代理
         */
        Sender sender = null;
//        sender = new LogProxy("hello", "google", new Date().toString());
        sender = new TimeCostProxy("hello", "google", new Date().toString());
        sender.send();
    }
}
