package com.xy.dp.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyTest {

    private Map<Integer, Calc> num2CalcMap;

    {
        num2CalcMap = new HashMap<>();
        num2CalcMap.put(0, new AddCalc());
        num2CalcMap.put(1, new SubCalc());
        num2CalcMap.put(2, new MulCalc());
        num2CalcMap.put(3, new DivCalc());
    }

    public int calc(int x, int y, int calcStrategy) {
        return num2CalcMap.get(calcStrategy).calc(x, y);
    }

    public static void main(String[] args) {
        StrategyTest test = new StrategyTest();

        System.out.println(test.calc(5, 5, 0));
        System.out.println(test.calc(5, 5, 1));
        System.out.println(test.calc(5, 5, 2));
        System.out.println(test.calc(5, 5, 3));
    }
}
