package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/3/19 15:55
 * @Description:
 */
public class 在O1时间内删除链表节点 {

    public static ListNode deleteNode(ListNode head, ListNode tobeDelete){
        if (head == null || tobeDelete == null){
            return head;
        }

        ListNode before = head;
        ListNode temp = head;
        // 如果删除的是头结点
        if (temp.val == tobeDelete.val){
            temp = temp.next;
            head = head.next;
        }
        // 如果非头结点
        while (temp != null){
            if (temp.val == tobeDelete.val){
                before.next = temp.next;
                temp = temp.next;
                continue;
            }
            before = temp;
            temp = temp.next;
        }
        return head;

    }

    public ListNode deleteNode2(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null)
            return null;
        if (tobeDelete.next != null) {
            // 要删除的节点不是尾节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            if (head == tobeDelete)
                // 只有一个节点
                head = null;
            else {
                ListNode cur = head;
                while (cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,3,3,4,3,5,6};
        ListNode head = LinklistUtil.create(nums);
        ListNode tobeDelete = new ListNode(3);
        LinklistUtil.printLinklistNode(deleteNode(head, tobeDelete));

    }
}
