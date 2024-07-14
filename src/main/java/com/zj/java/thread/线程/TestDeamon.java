package com.zj.java.thread.线程;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 05:20
 * 测试守护线程
 * + 线程分为用户线程和守护线程
 * + 虚拟机必须确保用户线程执行完毕
 * + 虚拟机不用等待守护线程执行完毕
 * + 如,后台记录操作日志,监控内存,垃圾回收等待..
 */
public class TestDeamon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        // 默认为 false，表示用户线程
        thread.setDaemon(true);
        thread.start();

        new Thread(you).start();


    }

}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你。");
        }
    }
}

class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("你一生都开心的活着。");
        }
    }
}