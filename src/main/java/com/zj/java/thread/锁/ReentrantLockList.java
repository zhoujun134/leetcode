package com.zj.java.thread.锁;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zhoujun
 * @Date: 2023/2/15 08:45
 */
public class ReentrantLockList {
    private final List<String> array = new ArrayList<>();
    // 独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // 添加元素
    public void add(String e) {
        writeLock.lock();
        try {
            array.add(e);
        } finally {
            writeLock.unlock();
        }
    }

    // 删除元素
    public void remove(String e) {
        writeLock.lock();
        try {
            array.remove(e);
        } finally {
            writeLock.unlock();
        }
    }

    // 获取数据
    public String get(int index) {
        readLock.lock();
        try {
            return array.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
