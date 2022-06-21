package com.xy.dp.builder;

public class BuilderTest {
    private static String testName = "ess33";
    private static String testIP = "127.0.0.1";
    private static String testMask = "255.255.255.0";
    private static String testMac = "0f:4c:62:2f:24:0e";

    public static void main(String[] args) {
        /**
         * opt类似于函数式编程中 function opts 是一种函数参数
         */
        IPBuilderOpt ipBuilderOpt = new IPBuilderOpt(testIP);
        MaskBuilderOpt maskBuilderOpt = new MaskBuilderOpt(testMask);
        MacBuilderOpt macBuilderOpt = new MacBuilderOpt(testMac);

        /**
         * 建造者模式 用于创建一个参数复杂, 并且存在必须/非必须的参数 的对象
         *
         * 待建造的类有一个静态类 来实例化一个创建类
         *
         * 创建类有一个build() 函数 用来创建对象
         *
         * 有意思的是build() 函数的参数列表
         *
         * 前部分是必须参数 后部分是opts build()方法体内部还可以为待创建的对象设置默认属性
         */
        NetEquipment netEquipment = NetEquipment.builder().build(testName,
                new NetEquipmentBuilderOPt[]{ipBuilderOpt, maskBuilderOpt, macBuilderOpt});
        System.out.println(netEquipment);
    }
}
