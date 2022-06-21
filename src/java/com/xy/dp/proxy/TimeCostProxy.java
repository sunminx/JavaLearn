package com.xy.dp.proxy;

//public class TimeCostProxy extends Sender{

/**
 * 这里耗时计算的代理类继承自另一个代理类 而不是直接继承被代理类
 *
 * 目的是形成一个 sender <- LogProxy <- TimeCostProxy 的继承树
 *
 * 这样形成了一个组合的代理关系(代理功能的顺序已经固定) 然后可以实现多个代理功能
 */
public class TimeCostProxy extends LogProxy {

    public TimeCostProxy(String message, String recvor, String date) {
        super(message, recvor, date);
    }

    public void send() {
        long start = System.currentTimeMillis();
        super.send();
        long end = System.currentTimeMillis();
        System.out.println("send timeCost: " + (end - start));
    }
}
