package com.zj.算法思想;

import com.zj.offer.链表.ListNode;
import com.zj.offer.链表.LinklistUtil;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {2, 3, 4, 5};
        ListNode link1 = LinklistUtil.create(nums1);
//        LinklistNode link2 = LinklistUtil.create(nums2);
//        LinklistNode res = func(link1, link2);
//        LinklistUtil.printLinklistNode(res);
//        System.out.println(FindKthToTail(link1, 6).val);
        TreeMap<Integer, String> pairs = new TreeMap<>();

        pairs.put(2, "B");

        pairs.put(1, "A");

        pairs.put(3, "C");
        String value = pairs.get(3);    //get method

        System.out.println(value);
    }

    public static ListNode func(ListNode l1, ListNode l2) {

        ListNode t1 = reverse(l1);
        ListNode t2 = reverse(l2);
        return merge(t1, t2);
    }


    /**
     * 链表的倒数 k 个节点
     *
     * @param pHead 链表
     * @param k     倒数 k 个节点
     * @return
     */
    public static ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here
        if (pHead == null) {
            return pHead;
        }
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        for (int i = 0; i < k; i++) {
            if (p1 == null) {
                return null;
            }
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 查找链表环的入口
     *
     * @param head 头结点
     * @return listNode
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                //利用快慢指针找相遇点
                ListNode slow2 = head;    //设置以相同速度的新指针从起始位置出发
                while (slow2 != slow) {      //未相遇循环。
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

    //合并
    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode res = newHead;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    newHead.next = l1;
                    l1 = l1.next;
                } else {
                    newHead.next = l2;
                    l2 = l2.next;
                }
                newHead = newHead.next;
            } else if (l1 != null && l2 == null) {
                newHead.next = l1;
                l1 = l1.next;
                newHead = newHead.next;
            } else if (l1 == null && l2 != null) {
                newHead.next = l2;
                l2 = l2.next;
                newHead = newHead.next;
            }
        }
        return res.next;
    }

    // 反转
    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode temp = head.next;
        head.next = null;
        ListNode temp2 = null;
        while (temp != null) {
            temp2 = temp.next;
            temp.next = head;
            head = temp;
            temp = temp2;
        }
        return head;
    }
}
