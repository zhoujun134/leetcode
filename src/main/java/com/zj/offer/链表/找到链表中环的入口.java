package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 15:22
 * @Description:
 */
public class 找到链表中环的入口 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 利用快慢指针找相遇点
            if (fast == slow) {
                // 设置以相同速度的新指针从起始位置出发
                ListNode slow2 = head;
                // 未相遇循环。
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

}
