package com.zj.offer.字符串.回文串;

import com.zj.offer.链表.ListNode;
import com.zj.offer.链表.LinklistUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/28 下午 10:42
 */
public class 回文链表 {
    public static void main(String[] args) {

//        int[] nums = {1, 2, 2, 1};
        int[] nums = {1, 2};
        ListNode head = LinklistUtil.create(nums);
        System.out.println(method1(head));

    }

    /**
     * 方法一
     * 将值复制到数组中后用双指针法
     * 一共为两个步骤：
     * 1. 复制链表值到数组列表中。
     * 2. 使用双指针法判断是否为回文。
     */

    public static boolean method1(ListNode head) {

        List<ListNode> nodeList = new ArrayList<>();
        ListNode temp = head;

        while (temp != null) {
            nodeList.add(temp);
            temp = temp.next;
        }

        for (int i = 0; i < nodeList.size() / 2; i++) {
            if (nodeList.get(i).val !=
                    nodeList.get((nodeList.size() - i - 1)).val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二
     * 使用递归
     */
    private static ListNode frontPointer;

    private static boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public static boolean method2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}
