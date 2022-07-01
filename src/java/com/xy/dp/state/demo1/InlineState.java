package com.xy.dp.state.demo1;

public class InlineState implements IState{
    @Override
    public String init(String input) {
        return "hello, i am tom";
    }

    @Override
    public String reply(String input) {
        return input;
    }
}
