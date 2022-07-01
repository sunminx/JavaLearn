package com.xy.dp.state.demo1;

/**
 * 状态接口定义了该状态的执行动作
 *
 * 这里将其进一步细分为状态变更时的动作和未变更时的动作
 * 分别为init()和reply()
 * 这个和定义一个事件驱动的动作的方法没有区别
 *
 * 这里状态接口没有定义事件驱动的状态变更的方法 原因是这部分实现在context中（两种方式之一）
 */
public interface IState {
    String init(String input);

    String reply(String input);
}
