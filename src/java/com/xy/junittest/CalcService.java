package com.xy.junittest;

public class CalcService {

    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 用于异常测试定义的一个方法
     * 减法运算 被减数 小 认为输入参数不合法
     * @param a
     * @param b
     * @return
     * @throws IllegalArgumentException
     */
    public int sub(int a, int b) throws IllegalArgumentException {
        if (a < b) {
            throw new IllegalArgumentException("a lt b");
        }
        return a - b;
    }
}
