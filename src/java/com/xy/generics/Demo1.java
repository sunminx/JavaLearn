package com.xy.generics;

/**
 * java的范型是基于"擦拭法"来实现的。
 *
 * 所有的范型类型都是源码层面的，编译器编译以后会擦除范型信息。
 * 或者说所有的范型类型T都是Object。
 *
 * 在其余获取T类型数据的地方 编译器都进行了类型安全的强转
 *
 * 泛型方法要防止重复定义方法，例如：public boolean equals(T obj)；
 *
 * 范型可以继承 子类（不显示定义范型） 默认继承父类的范型声明
 */
public class Demo1 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 范型不支持基础数据类型
     * Object无法持有基础数据类型
     */
    public static void test0() {
        // new Pair1<int>();
    }

    /**
     * 无法取得带范型的Class
     */
    public static void test1() {
        // Pair1<String>.class;
        Pair1<String> p1 = new Pair1<>("tom", "kim");
        Pair1<Integer> p2 = new Pair1<>(10, 41);

        Class<? extends Pair1> c1 = p1.getClass();
        Class<? extends Pair1> c2 = p2.getClass();

        System.out.println(c1 == c2);
        System.out.println(c1 == Pair1.class);
    }

    public static void test2() {
        Pair1<String> p1 = new Pair1<>("tom", "kim");

        // 无法进行带范型的类型判断
        // if (p1 instanceof Pair1<String>) {}
    }

    /**
     *  无法实例化 范型T
     */
    public static void test3() {

    }
}
