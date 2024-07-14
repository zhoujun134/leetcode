package com.zj.java.thread.多线程.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/31 下午 09:44
 */
public class NewCachedThreadPoolTest {
    public static void main(String[] args) {
        // 回收型线程池，可以重复利用之前创建过的线程，运行线程最大数是Integer.MAX_VALUE。
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.execute(() -> {
                System.out.println(index);
            });
        }

        /**
         * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
         */
    }
}
