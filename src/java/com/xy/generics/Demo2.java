package com.xy.generics;

/**
 * <? extends Number>
 *     1. 用于方法参数 只能读不能写
 *     2. 用于范型类 限定实际类型只能是Number或者Number子类
 *
 * <? super Number>
 *     1. 用于方法参数 只能写不能读
 *
 * 范型中 extends super 通配符 遵循 PECS（Provider <返回 T> Extends Consumer <写入 T> Super）原则
 *
 * <?> 无限定通配符 很少使用 一般使用<T>代替
 *
 */
public class Demo2 {

    public static void setPair(Pair1<? super Integer> pair, Integer first, Integer second) {
        pair.setFirst(first);
        pair.setSecond(second);
    }

    public static Number[] getPair(Pair1<? extends Number> pair) {
        return new Number[]{pair.getFirst(), pair.getSecond()};
    }
}
