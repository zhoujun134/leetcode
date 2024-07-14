package com.zj.java.thread.集合;

import test.util.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhoujun
 * @Date: 2023/3/5 19:06
 */
public class TestConcurrentHashMap {

    // (1) 创建 map， key 为 topic，value 为设备列表
    private final static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // 进入直播间 topic1， 线程 one
        Thread threadOne = new Thread(() -> {
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device1");
            list1.add("device2");
//            map.put("topic1", list1);
            List<String> oldList = map.putIfAbsent("topic1", list1);
            if (Objects.nonNull(oldList)) {
                oldList.addAll(list1);
            }
            System.out.println("thread 1 " + GsonUtils.toJson(map));
        });
        // 进入直播间 topic1，线程 two
        Thread threadTwo = new Thread(() -> {
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device11");
            list1.add("device22");
//            map.put("topic1", list1);
            List<String> oldList = map.putIfAbsent("topic1", list1);
            if (Objects.nonNull(oldList)) {
                oldList.addAll(list1);
            }
            System.out.println("thread 2 " + GsonUtils.toJson(map));
        });
        // 进入直播间 topic2，线程 three
        Thread threadThree = new Thread(() -> {
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("device111");
            list1.add("device222");
//            map.put("topic2", list1);
            List<String> oldList = map.putIfAbsent("topic2", list1);
            if (Objects.nonNull(oldList)) {
                oldList.addAll(list1);
            }
            System.out.println("thread 3 " + GsonUtils.toJson(map));
        });
        // 启动线程
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }

}
