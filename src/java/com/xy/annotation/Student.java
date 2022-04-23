package com.xy.annotation;

import java.lang.reflect.Field;

public class Student {

    private String name;

    private int age;

    @Range(value = 65, min = 3, max = 150)
    private int price;

    /**
     * 注解对应的处理方法
     * @Range 是运行时可读取的注解
     * @throws IllegalAccessException
     */
    void check() throws IllegalAccessException {
        for (Field field : this.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                Object val = field.get(this);
                if (val instanceof Integer) {
                    int v = (int) val;
                    if (v > range.max() || v < range.min()) {
                        throw new IllegalArgumentException("invalid price");
                    }
                }
            }
        }
    }
}
