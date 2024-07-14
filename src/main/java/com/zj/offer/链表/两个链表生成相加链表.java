package com.zj.offer.链表;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/09 下午 12:59
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * <p>
 * 输入：
 * [9,3,7],[6,3]
 * 返回值：
 * {1,0,0,0}
 */
public class 两个链表生成相加链表 {

    public static void main(String[] args) {
        int[] head1Arr = {9, 3};
        int[] head2Arr = {6, 3};
        ListNode head1 = LinklistUtil.create(head1Arr);

        ListNode head2 = LinklistUtil.create(head2Arr);
        ListNode result = addInList(head1, head2);
        LinklistUtil.printLinklistNode(result);
    }

    /**
     * @param head1 ListNode类
     * @param head2 ListNode类
     * @return ListNode类
     */
    public static ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode temp1 = reverse(head1);
        ListNode temp2 = reverse(head2);
        ListNode result = new ListNode(-1);
        ListNode resultHead = result;
        int isAddTen = 0;
        while (temp1 != null && temp2 != null) {
            int val = temp1.val + temp2.val + isAddTen;
            if (val >= 10){
                isAddTen = 1;
                val -= 10;
            }else {
                isAddTen = 0;
            }
            result.next = new ListNode(val);
            result = result.next;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        // 处理 temp1
        while (temp1 != null){
            int val = temp1.val + isAddTen;
            if (val >= 10){
                isAddTen = 1;
                val -= 10;
            }else {
                isAddTen = 0;
            }
            result.next = new ListNode(val);
            result = result.next;
            temp1 = temp1.next;
        }
        // 处理 temp2
        while (temp2 != null){
            int val = temp2.val + isAddTen;
            if (val >= 10){
                isAddTen = 1;
                val -= 10;
            }else {
                isAddTen = 0;
            }
            result.next = new ListNode(val);
            result = result.next;
            temp2 = temp2.next;
        }

        if (isAddTen > 0){
            result.next = new ListNode(isAddTen);
        }

        return reverse(resultHead.next);
    }

    /**
     * 反转链表---头插法反转
     *
     * @param head 需要反转的链表
     * @return 反转之后的链表
     */
    public static ListNode reverse(ListNode head) {
        // 指针
        ListNode cur = head;
        ListNode result = new ListNode(-1);
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = result.next;
            result.next = cur;
            cur = temp;
        }
        return result.next;
    }
}
