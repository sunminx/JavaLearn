package com.xy.dp.iter;

/**
 * 将遍历集合的算法抽取出来，成为一个单独的迭代器类
 * 正常的做法是将集合遍历的方法定义在集合类中, 迭代器类的做法是通过组合的方式获取集合的访问链接，同时将遍历算法单独定义在迭代器类中
 * 优点是遍历算法独立于集合类，一旦遍历算法变化，可以不改变集合类, 而是新增一个迭代器
 * 便于扩展遍历算法
 */
public class IterTest {

    public static void main(String[] args) {
        Integer[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        IterableArray<Integer> arr = new IterableArray<Integer>(nums);

        OneStepIterator<Integer> oneStepIterator = new OneStepIterator<>(arr);
        TwoStepIterator<Integer> twoStepIterator = new TwoStepIterator<>(arr);

        // Iterator iterator = oneStepIterator;
        Iterator iterator = twoStepIterator;

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
