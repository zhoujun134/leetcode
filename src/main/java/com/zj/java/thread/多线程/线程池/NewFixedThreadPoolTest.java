package com.zj.java.thread.多线程.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/31 下午 09:49
 */
public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        }

        /**
         * 因为线程池大小为3，每个任务输出 index 后 sleep 2 秒，所以每两秒打印3个数字。
         * 定长线程池的大小最好根据系统资源进行设置。如 Runtime.getRuntime().availableProcessors()。
         */

    }
}
