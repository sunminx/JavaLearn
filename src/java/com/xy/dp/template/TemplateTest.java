package com.xy.dp.template;

/**
 * 模版模式: 父类的方法定义好事情的处理流程 然后具体的抽象子流程方法由具体的子类去实现
 * 不同的子类会有不同的子事情处理细节 但是整体来说 整件事的处理流程不变
 *
 * 基于继承来实现
 *
 * 这个非常像责任链模式 只不过责任链中事情的处理流程是由初始化的处理器链的先后顺序决定
 * 而模版模式中时期的处理流程由父类中的模版方法来决定
 *
 * 模版模式运行各个子事件方法描述不一致
 * 而责任链中一般是实现自同一个接口方法
 */
public class TemplateTest {

    public static void main(String[] args) {
        int port = 4988;
        String address = "No.333 Heidelberg Post Office";
        PostOffice postOffice = new HeidelbergPostOffice(port, address);

        Letter letter = new Letter("hello marry".getBytes(), 8100, "Berlin");

        postOffice.process(letter);
    }
}
