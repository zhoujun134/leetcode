package com.zj.java.thread.线程.syn;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 05:47
 * 不安全的买票方式
 * 会出现错误的买票数量
 */
public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "a").start();
        new Thread(buyTicket, "b").start();
        new Thread(buyTicket, "c").start();

    }
}

class BuyTicket implements Runnable {
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

    private void buy() {
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
