package com.xy.multithread;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture 异步获取处理结果
 * 可以设置回调函数 当任务处理完成或者异常退出后 将会执行响应的回调函数
 * 此时 任务调用者不需要关心任务什么时候结束，然后再继续后续的处理
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                return queryCode("中国石油");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        completableFuture.thenAccept((code)->{
            System.out.println("股价: " + queryPrice(code));
        });

        Thread.sleep(2000);
    }

    static String queryCode(String name) throws InterruptedException {
        Thread.sleep(1000);
        return "356789";
    }

    static BigDecimal queryPrice(String code) {
        return BigDecimal.valueOf(5 + Math.random() * 100);
    }
}
