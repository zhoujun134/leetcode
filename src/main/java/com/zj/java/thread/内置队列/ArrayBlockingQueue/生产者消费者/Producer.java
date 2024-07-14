package com.zj.java.thread.内置队列.ArrayBlockingQueue.生产者消费者;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 */
public class Producer implements Runnable {
    private BlockingQueue<String> queue;

    private volatile boolean isRunning = true;

    private static final AtomicInteger count = new AtomicInteger();

    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {

        String data = null;
        System.out.println("启动生产者线程！");
        Random random = new Random();
        try {
            while (isRunning) {
                System.out.println("正在生产数据...");
                Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = "data:" + count.incrementAndGet();
                System.out.println("将数据" + data + "放入队列...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败:" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }

}