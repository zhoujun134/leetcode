package com.zj.java.thread.线程.syn;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 05:59
 * 不安全的银行取钱
 * 两个人去银行取钱，账户
 */
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");

        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "girlFriend");

        // 启动
        you.start();
        girlFriend.start();

    }
}

// 账户
@Data
@AllArgsConstructor
class Account {
    // 余额
    int money;
    // 卡号
    String name;
}

// 银行
@AllArgsConstructor
class Drawing extends Thread {
    // 账户
    Account account;
    // 取了多少钱
    int drawingMoney;
    // 还剩多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {
        // 判断有没有钱
        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
            return;
        }

        // 模拟延时, 可以放大问题的发现性
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 卡内余额 = 余额 - 你取出的钱
        account.money -= drawingMoney;
        // 你手里的钱
        nowMoney += drawingMoney;
        System.out.println(this.getName() + " ======> " + account.name + " 余额为: " + account.money);
        // 这里 Thread.currentThread().getName() == this.getName()
        System.out.println(this.getName() + " 手里的钱: " + nowMoney);
    }
}