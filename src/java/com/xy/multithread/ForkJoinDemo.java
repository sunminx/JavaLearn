package com.xy.multithread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkJoinPool 接受一个任务，按照分治的思想，对任务进行拆分，拆成子任务给不同对线程执行
 * 最后由下而上逐层将子任务结果进行合并。
 * 相较于单线程执行 执行时间大大缩小 13000 -> 2000
 *
 * 这里每做一次加法sleep 1ms来拖慢每次运算执行时间。
 * 如果不这么做，可以发现单线程比线程池跑同样对任务更快。侧面反应了是否使用多线程 要考虑多线程带来对收益是不是大于线程切换的成本。
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        long[] nums = randomArray(10000);

        long sum1 = testSingleThread(nums, 0, nums.length);
        long sum2 = testForkJoin(nums, 0, nums.length);

        System.out.printf("sum1 = %d sum2 = %d\n", sum1, sum2);
    }

    private static long testSingleThread(long[] nums, int start, int end) {
        System.out.println("single thread calc num start");
        long s = System.currentTimeMillis();

        long sum = 0;
        for (int i = start; i < end; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += nums[i];
        }
        System.out.println("calc end; timeCost: " + (System.currentTimeMillis() - s));
        return sum;
    }

    private static long testForkJoin(long[] nums, int start, int end) {
        System.out.println("fork join calc sum start");
        long s = System.currentTimeMillis();
        SumTask sumTask = new SumTask(nums, start, end);
        long sum = ForkJoinPool.commonPool().invoke(sumTask);
        System.out.println("calc end; timeCost: " + (System.currentTimeMillis() - s));
        return sum;
    }

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 500;

        private final long[] nums;
        private final int start;
        private final int end;

        public SumTask(long[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            int gap = end - start;

            if (gap <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum += nums[i];
                }
                return sum;
            }

            int mid = start + (end - start) / 2;

            SumTask sumTask1 = new SumTask(nums, start, mid);
            SumTask sumTask2 = new SumTask(nums, mid, end);

            invokeAll(sumTask1, sumTask2);

            sum += sumTask1.join();
            sum += sumTask2.join();

            return sum;
        }
    }

    private static long[] randomArray(int size) {
        Random random = new Random();

        long[] nums = new long[size];

        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(100);
        }
        return nums;
    }
}
