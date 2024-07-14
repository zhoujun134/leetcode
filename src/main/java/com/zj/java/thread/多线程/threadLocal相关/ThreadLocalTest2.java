package com.zj.java.thread.多线程.threadLocal相关;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-08-24
 */
public class ThreadLocalTest2 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        // 创建一个有2个核心线程数的线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2, 1,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10));
        // 线程池提交一个任务，将任务序号及执行该任务的子线程的线程名放到 ThreadLocal 中
        threadPool.execute(() -> threadLocal.set("任务1: " + Thread.currentThread().getName()));
        threadPool.execute(() -> threadLocal.set("任务2: " + Thread.currentThread().getName()));
        threadPool.execute(() -> threadLocal.set("任务3: " + Thread.currentThread().getName()));
        // 输出 ThreadLocal 中的内容
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println("ThreadLocal value of " +
                    Thread.currentThread().getName() + " = " + threadLocal.get()));
        }
        // 线程池记得关闭
        threadPool.shutdown();
    }
}
