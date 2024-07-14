package com.zj.java.thread.多线程.线程池;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujun
 * @Date: 2023/3/5 18:13
 */
public class TestCyclicBarrier {

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, ()-> {
        System.out.println(Thread.currentThread() + "task1 merge result ");
    });
    public static void main(String[] args) {
        // 创建一个线程个数固定为 2 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 将 A 线程添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "task1-1");
                System.out.println(Thread.currentThread() + "enter barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + "enter out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        // 将 B 线程添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "task1-2");
                System.out.println(Thread.currentThread() + "enter barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread() + "enter out barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        // 关闭线程池
        executorService.shutdown();
    }
}
