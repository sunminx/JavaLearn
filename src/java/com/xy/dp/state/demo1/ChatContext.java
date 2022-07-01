package com.xy.dp.state.demo1;

/**
 * ChatContext是状态模式UML中的context
 * 内部包括一个state实例（聊天对象某个时刻的状态是唯一的，要么在线要么下线；并且这个状态的变更是不频繁的（无需对状态对象进行缓存））
 * 负责根据事件来完成状态的切换（状态切换的实现形式之一，另外一种是由具体的state负责完成状态切换（这种一般是状态切换比较固定，一个状态知道切换到哪一个状态，比如线性工作流，那么切到下一个状态即可））
 *
 * 事件驱动状态切换和动作发生（当然并不是一定会发生，意味着某个事件发生并不一定会导致状态变化，也不一定会有动作要发生）
 * 但是这个动作由具体的状态类来实现 context只是根据当前状态来调用状态对象的实现
 */
public class ChatContext {

    private IState state;

    /**
     * 事件驱动状态变更的实现
     * @param input
     */
    public String handle(String input) {
        if ("hello".equalsIgnoreCase(input)) {
            /**
             * 输入为"hello"的事件驱动了状态发生变化
             */
            state = new InlineState();

            return state.init(input);
        }

        if ("bye".equalsIgnoreCase(input)) {
            /**
             * 输入为"bye"的事件驱动状态发生变化
             */
            state = new OutlineState();

            return state.init(input);
        }

        /**
         * 事件（输入）没有导致状态切换，但是还是可能导致动作发生
         */
        return chat(input);
    }

    /**
     * 事件导致的动作发生 调用相应具体的状态实现
     * @param input
     * @return
     */
    public String chat(String input) {
        return state.reply(input);
    }
}
