package com.xy.generics;

/**
 * 范型和反射
 */
public class Demo3 {

    public static void main(String[] args) {
        test0();
    }

    public static void test0() {
//        Class clazz = String.class;
//        String str = (String) clazz.newInstance();

//        Class<String> clazz = String.class;
//        String str = clazz.newInstance();
    }

    public static void test1() {
        // 带范型带数组可以声明
        Pair1<String> arr1 = null;

        // 带范型的数组 不可以直接创建
        // Pair1<String> arr2 = new Pair1<String>[2];

        // 通过强转可以创建带范型的数组

        // right
        Pair1<String>[] arr3 = (Pair1<String>[]) new Pair1[2];
        // wrong
        // 不要显示定义arr4 然后强转成带范型的数组
        // 这样操作arr4可能会造成arr5取值CastException
        Pair1[] arr4 = new Pair1[2];
        Pair1<String>[] arr5 = (Pair1<String>[]) arr4;
    }
}
