package com.xy.dp.strategy;

public class SubCalc implements Calc{

    @Override
    public int calc(int x, int y) {
        return x - y;
    }
}
