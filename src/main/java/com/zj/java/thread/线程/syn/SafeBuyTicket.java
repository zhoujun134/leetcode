package com.zj.java.thread.线程.syn;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 05:47
 *
 * 由于我们可以通过 private 关键字来保证数据对象只能被方法访问 , 所以我们只需
 * 要针对方法提出一套机制 , 这套机制就是 synchronized 关键字 , 它包括两种用法 :
 * synchronized 方法 和synchronized 块 .
 */
public class SafeBuyTicket {
    public static void main(String[] args) {
        MySafeBuyTicket buyTicket = new MySafeBuyTicket();
        new Thread(buyTicket, "a").start();
        new Thread(buyTicket, "b").start();
        new Thread(buyTicket, "c").start();

    }
}

class MySafeBuyTicket implements Runnable {
    // 外部停止方式
    boolean flag = true;

    // 票的数量
    int ticketNumber = 10;

    @Override
    public void run() {
        // 买票
        while (flag){
            buy();
        }
    }

    // synchronized 锁的是对象 this
    private synchronized void buy() {
        // 判断是否有票
        if (ticketNumber <= 0){
            flag = false;
            return;
        }
        // 模拟延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 买票
        System.out.println(Thread.currentThread().getName()+ "拿到 " + ticketNumber--);
    }
}
