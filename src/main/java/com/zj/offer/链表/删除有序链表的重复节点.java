package com.zj.offer.链表;

/**
 * @Author: zhoujun
 * @Date: 2021/3/25 15:30
 * @Description:
 * 一个链表（有序），删除其中的重复元素
 */
public class 删除有序链表的重复节点 {

    public static void main(String[] args) {
        int[] nums = {1};
        ListNode head = LinklistUtil.create(nums);
        ListNode head2 = removeData(head);

        LinklistUtil.printLinklistNode(head2);
    }

    public static ListNode removeData(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = head.next;
        ListNode before = head;
        while (temp != null){
            if (temp.val == before.val){
                before.next = temp.next;
                temp = temp.next;
                continue;
            }
            temp = temp.next;
            before = before.next;
        }
        return head;
    }

}
