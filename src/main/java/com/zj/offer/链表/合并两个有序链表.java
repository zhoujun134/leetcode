package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 15:37
 * @Description:
 * 将两个有序的链表合并为一个新链表，
 * 要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序。
 */
public class 合并两个有序链表 {

    public static void main(String[] args) {
        int[] numsl1 = {1,2,3,4,5};
        int[] numsl2 = {1,2,4,5,7};
        ListNode l1 = LinklistUtil.create(numsl1);
        ListNode l2 = LinklistUtil.create(numsl2);
        LinklistUtil.printLinklistNode(mergeTwoLists(l1, l2));
    }
    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 处理头结点
        ListNode head = l1.val < l2.val ? l1: l2;
        ListNode tail = head;

        // 更新节点
        l1 = l1 == head ? l1.next : l1;
        l2 = l2 == head ? l2.next : l2;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // 处理是否为空的节点
        tail.next = l1 == null ? l2 : l1;
        return head;
    }


}
