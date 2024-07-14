package com.zj.offer.链表;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhoujun
 * @Date: 2021/3/18 15:46
 * @Description:
 *
 */
public class 从尾到头打印链表 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode listNode = LinklistUtil.create(nums);
        printListFromTailToHead(listNode).forEach(System.out:: println);
//        printListFromTailToHead3(linklistNode).forEach(System.out:: println);

    }

    /**
     * 方法一：使用递归
     * 要逆序打印链表 1->2->3（3,2,1)，
     * 可以先逆序打印链表 2->3(3,2)，
     * 最后再打印第一个节点 1。
     * 而链表 2->3 可以看成一个新的链表，
     * 要逆序打印该链表可以继续使用求解函数，
     * 也就是在求解函数中调用自己，这就是递归函数。
     * @param listNode 链表的头结点
     * @return
     */
    public static List<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (listNode != null) {
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        return ret;

    }

    /**
     * 方法二 使用头插法
     * @param head 链表的头结点
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode head){
        ArrayList<Integer> ret = new ArrayList<>();
        // 新结点
        ListNode newHead = new ListNode(-1);
        while (head != null){
            // 存储下一个节点
            ListNode temp = head.next;
            // 头插入
            head.next = newHead.next;
            newHead.next = head;
            head = temp;
        }
        ListNode temp2 = newHead.next;
        while (temp2 != null){
            ret.add(temp2.val);
            temp2 = temp2.next;
        }
        return ret;
    }


    /**
     * 方法三 使用栈
     * 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。
     */
    public static ArrayList<Integer> printListFromTailToHead3(ListNode head){
        ArrayList<Integer> ret = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (head!= null){
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.isEmpty())
            ret.add(stack.pop());
        return ret;
    }
}
