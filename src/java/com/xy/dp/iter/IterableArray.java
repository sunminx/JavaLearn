package com.xy.dp.iter;

public class IterableArray<T> {
    private T[] eles;

    public IterableArray(T[] eles) {
        this.eles = eles;
    }

    public int len() {
        return eles.length;
    }

    public T get(int idx) {
        return eles[idx];
    }
}
