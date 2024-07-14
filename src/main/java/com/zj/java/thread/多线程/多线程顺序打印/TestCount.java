package com.zj.java.thread.多线程.多线程顺序打印;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCount implements Runnable {
    // 同步变量
    public final AtomicInteger sycCount;
    // 线程名称
    private final String name;
    // 线程标识
    private final int flag;
    //统计打印的次数。
    private int count = 0;

    public TestCount(String name, AtomicInteger sycCount, int flag) {
        this.name = name;
        this.sycCount = sycCount;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sycCount) {
                if (sycCount.get() % 3 == flag) {
                    sycCount.set(sycCount.get() + 1);
                    System.out.println(name + "---" + sycCount.get());
                    count++;
                    sycCount.notifyAll();
                    if (count == 10) {
                        break;
                    } else {
                        try {
                            sycCount.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}