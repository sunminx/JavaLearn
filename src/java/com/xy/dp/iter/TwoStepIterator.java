package com.xy.dp.iter;

public class TwoStepIterator<T> implements Iterator<T>{
    private IterableArray<T> arr;
    private int idx;

    public TwoStepIterator(IterableArray<T> arr) {
        this(arr, 0);
    }

    public TwoStepIterator(IterableArray<T> arr, int idx) {
        this.arr = arr;
        this.idx = idx;
    }

    @Override
    public boolean hasNext() {
        return idx < arr.len();
    }

    @Override
    public T next() {
        T ele = arr.get(idx);
        idx += 2;
        return ele;
    }
}
