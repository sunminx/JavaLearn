package com.xy.multithread;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

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
