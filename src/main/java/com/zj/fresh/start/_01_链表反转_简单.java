package com.zj.fresh.start;

import com.zj.fresh.start.common.ListNode;

import java.util.Stack;

/**
 * @ClassName 链表反转
 * @Author zj
 * @Description
 * @Date 2025/3/11 23:40
 * @Version v1.0
 **/
public class _01_链表反转_简单 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createListNode(arr);
        printListNode(head);
        System.out.println("进行翻转");
//        ListNode newHeadNode = reversByStack(head);
        ListNode newHeadNode = reversByTwoLink(head);
        printListNode(newHeadNode);

    }

    private static ListNode createListNode(int[] arr) {

        ListNode head = null;
        ListNode tempHead = head;
        for (int j : arr) {
            if (head == null) {
                head = new ListNode(j);
                tempHead = head;
                continue;
            }
            ListNode curNode = new ListNode(j);
            tempHead.next = curNode;
            tempHead = curNode;
        }

        return head;
    }

    private static void printListNode(ListNode head) {
        ListNode temp = head;

        if (temp == null) {
            return;
        }
        StringBuilder string = new StringBuilder();
        while (temp != null) {
            string.append(temp.val);
            temp = temp.next;
        }
        System.out.println(string);
    }

    /**
     * 通过栈实现
     * @param head 头节点
     * @return 反转之后的链表
     */
    private static ListNode reversByStack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        ListNode newHead = stack.pop();
        ListNode temp2 = newHead;
        temp2.next = null;
        while (!stack.isEmpty()) {
            ListNode curPopNode = stack.pop();
            if (curPopNode != null) {
                curPopNode.next = null;
                temp2.next = curPopNode;
                temp2 = temp2.next;
            }
        }
        return newHead;
    }


    /**
     * 方法二 双链表解决
     * @param head 头结点
     * @return 反转之后的链表
     */
    private static ListNode reversByTwoLink(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tempHead = head.next;
            head.next = newHead;
            newHead = head;
            head = tempHead;

        }
        return newHead;
    }

    /**
     * 方法三 递归求解
     * @param head 头结点
     * @return 反转之后的链表
     */
    private static ListNode reversByRecursion(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 保存当前节点的下一个节点
        ListNode next = head.next;
        // 从当前节点的下一个结点开始递归调用
        ListNode curReverse = reversByRecursion(next);

        // curReverse 是反转之后的链表，因为函数 reversByRecursion
        // 表示的是对链表的反转，所以反转完之后 next 肯定
        // 是链表 reverse 的尾结点，然后我们再把当前节点
        // head 挂到 next 节点的后面就完成了链表的反转。
        next.next = head;
        // 这里 head 相当于变成了尾结点，尾结点都是为空的，
        // 否则会构成环
        head.next = null;
        return curReverse;
    }
}
