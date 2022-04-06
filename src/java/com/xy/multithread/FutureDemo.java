package com.xy.multithread;

import java.util.concurrent.*;

/**
 * 使用submit向线程池提交一个有返回值的任务
 * submit方法返回一个Future
 * 然后在主线程中可以通过Future来拿到任务的返回值
 * 当然 如果任务未结束 主线程需要阻塞在future.get()方法上
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Task());

        // 这里等于是阻塞轮询获取任务处理结果
        // 虽然任务是异步执行的，但是获取任务结果认识同步阻塞的
        String result;
        while (true) {
            try {
                result = future.get(500, TimeUnit.MILLISECONDS);
                break;
            } catch (TimeoutException e) {
                System.out.println("wait 500 mills");
            }
        }

        System.out.println("结果: " + result);

        Thread.sleep(5000);
    }


    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "testResult";
        }
    }
}
