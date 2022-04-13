package com.xy.generics;

/**
 * 范型类同时支持 定义多种类型的范型
 * @param <K>
 * @param <V>
 */
public class Pair2<K, V> {
    private K key;
    private V val;

    public Pair2(K k, V v) {
        this.key = k;
        this.val = v;
    }

    public static <K, V> Pair2<K, V> createPair(K k, V v) {
        return new Pair2<>(k, v);
    }
}
