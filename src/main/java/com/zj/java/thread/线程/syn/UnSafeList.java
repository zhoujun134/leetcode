package com.zj.java.thread.线程.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 06:20
 */
public class UnSafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> unSafeList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> unSafeList.add(Thread.currentThread().getName()))
                    .start();
        }
        Thread.sleep(3000);
        System.out.println("size: " + unSafeList.size());
    }
}
