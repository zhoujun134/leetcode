package com.zj.java.thread.多线程.线程池;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/31 下午 09:32
 */
public class NewScheduledThreadPoolTest {

    public static void main(String[] args) {
        // ScheduledExecutorService比Timer更安全，功能更强大，
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        // 延时任务
        scheduledExecutorService.schedule(() -> {
            System.out.println("延迟三秒执行");
        }, 3, TimeUnit.SECONDS);

        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("延迟 1 秒后每三秒执行一次");
        }, 1,3, TimeUnit.SECONDS);


        scheduledExecutorService.submit(() -> {
            System.out.println("立即执行");
        });
    }
    @Test
    public void test() {


    }

}
