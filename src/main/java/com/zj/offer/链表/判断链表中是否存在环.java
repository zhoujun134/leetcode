package com.zj.offer.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhoujun
 * @Date: 2021/3/19 17:02
 * @Description:
 * 题目描述
 * 一个链表中包含环，请找出该链表的环的入口结点。要求不能使用额外的空间。
 *
 */
public class 判断链表中是否存在环 {
    public static void main(String[] args){


    }

    /**
     * 方法一：快慢指针的方式
     * 最简单的一种方式就是快慢指针，
     * 慢指针针每次走一步，
     * 快指针每次走两步，
     * 如果相遇就说明有环，如果有一个为空说明没有环。
     * 判断链表中是否存在环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }

        // 依次定义快慢节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            // 慢指针每次走一步
            slow = slow.next;
            // 快指针每次走两步
            fast = fast.next.next;
            // 如果相遇
            if (fast == slow){
                return true;
            }
        }
        // 走完之后，无环存在
        return false;
    }

    /**
     * 存放集合中
     * 把节点存放到集合set中，每次存放的时候判断当前节点是否存在，
     * 如果存在，说明有环，直接返回true，比较容易理解
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {

        Set<ListNode> sets = new HashSet<>();
        while (head != null){
            // 如果包含当前节点，说明存在环
            if (sets.contains(head)){
                return true;
            }
            sets.add(head);
            head = head.next;
        }
        // 如果不存在环
        return false;
    }
}
