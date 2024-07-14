package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/4/7 20:44
 * @Description:
 */
public class 删除链表中倒数的k个结点 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            return null;
        }
        ListNode p = head;
        ListNode q = head;
        ListNode pre = null;
        while (n > 0) {
            if (p != null) {
                p = p.next;
            } else {
                return null;
            }
            n--;
        }
        if (p == null) {
            return head.next;
        }
        while (p != null) {
            p = p.next;
            pre = q;
            q = q.next;
            if (p == null) {
                pre.next = q.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};

        ListNode head = LinklistUtil.create(nums);
        ListNode reHead = removeNthFromEnd(head, 1);
        LinklistUtil.printLinklistNode(reHead);

    }
}
