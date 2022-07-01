package com.xy.dp.state.demo1;

public class OutlineState implements IState{
    @Override
    public String init(String input) {
        return "bye! see you";
    }

    @Override
    public String reply(String input) {
        return input;
    }
}
