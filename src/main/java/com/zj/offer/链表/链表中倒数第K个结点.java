package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/3/19 16:28
 * @Description:
 * 设链表的长度为 N。设置两个指针 P1 和 P2，
 * 先让 P1 移动 K 个节点，则还有 N - K 个节点可以移动。
 * 此时让 P1 和 P2 同时移动，可以知道当 P1 移动到链表结尾时，
 * P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点。
 */
public class 链表中倒数第K个结点 {


    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null){
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        ListNode head = LinklistUtil.create(nums);
        LinklistUtil.printLinklistNode(findKthToTail(head, 1));

    }
}
