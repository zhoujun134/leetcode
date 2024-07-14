package com.zj.java.thread.unsafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-02-09
 * 统计0的个数
 */
public class AtomicTest {
    // (10) 创建 Long 型原子计数器
    private static final AtomicLong atomicLong = new AtomicLong();
    // (11) 创建数据源
    private static final Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static final Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        //（12）线程 one 统计数组 arrayOne 中 0 的个数
        Thread threadOne = new Thread(() -> {
            for (Integer integer : arrayOne) {
                if (integer == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });
        //（13）线程 two 统计数组 arrayTwo 中 0 的个数
        Thread threadTwo = new Thread(() -> {
            for (Integer integer : arrayTwo) {
                if (integer == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });
        // (14) 启动子线程
        threadOne.start();
        threadTwo.start();
        // (15) 等待线程执行完毕
        threadOne.join();
        threadTwo.join();
        System.out.println("总共有 count 0:" + atomicLong.get());
    }
}
