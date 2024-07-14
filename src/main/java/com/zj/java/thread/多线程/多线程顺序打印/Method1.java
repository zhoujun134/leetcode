package com.zj.java.thread.多线程.多线程顺序打印;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/22 下午 12:52
 * 使用原子类 `AtomicInteger`类 ,`AtomicInteger`类线程安全,适合在高并发中当计数器.
 */
public class Method1 {
    public static void main(String[] args) {
        AtomicInteger synCount = new AtomicInteger(0);

        new Thread(new TestCount("A", synCount, 0)).start();
        new Thread(new TestCount("B", synCount, 1)).start();
        new Thread(new TestCount("C", synCount, 2)).start();
    }
}
