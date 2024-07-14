package com.zj.java.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: zhoujun
 * @Date: 2023/3/5 19:18
 * SimpleDateFormat 多线程问题
 */
public class TestSimpleDateFormat {
    // 创建但实例
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // 创建多个线程，并启动
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(sdf.parse("2023-03-05 19:21:31"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            // 启动线程
            thread.start();
        }
    }
}
