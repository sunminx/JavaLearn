package com.xy.generics;

/**
 * 范型类的demo
 * @param <T>
 */
public class Pair1<T> {
    private T first;
    private T second;

    public Pair1(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getFirst() {
        return first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public T getSecond() {
        return second;
    }

    public String toString() {
        return "pair: first = " + first + " second = " + second;
    }

    /**
     * 静态方法 中的范型标记 K 和类定义中范型 T 完全没有关系
     * K 的具体类型看传参的类型 而 实例具体的类型看 K 的类型
     *
     * 静态方法的返回参数描述前面要加一个 范型说明 <T> 不然无法通过编译
     * @param first
     * @param second
     * @param <K>
     * @return
     */
    public static <K> Pair1<K> createPair(K first, K second) {
        return new Pair1<>(first, second);
    }

    public static void main(String[] args) {
        Pair1<String> pair1 = Pair1.createPair("tom", "kim");
        System.out.println(pair1);
        Pair1<Integer> pair2 = Pair1.createPair(12, 23);
        System.out.println(pair2);
    }

}
