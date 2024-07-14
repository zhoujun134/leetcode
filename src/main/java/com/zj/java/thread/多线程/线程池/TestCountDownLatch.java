package com.zj.java.thread.多线程.线程池;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujun
 * @Date: 2023/3/4 14:20
 */
public class TestCountDownLatch {

    private final static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 将线程 A 添加到线程池
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("children A thread is down！");
        });
        // 将线程 B 添加到线程池
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("children B thread is down！");
        });
        System.out.println("wait children is down!");
        countDownLatch.await();
        System.out.println("main is down");
        executorService.shutdown();
    }
}
