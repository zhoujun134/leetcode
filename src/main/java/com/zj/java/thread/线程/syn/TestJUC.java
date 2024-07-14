package com.zj.java.thread.线程.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 06:32
 * 测试 JUC(CopyOnWriteArrayList) 安全类型的集合
 */
public class TestJUC {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName()))
                    .start();
        }
        Thread.sleep(3000);
        System.out.println("size: " + list.size());

    }
}
