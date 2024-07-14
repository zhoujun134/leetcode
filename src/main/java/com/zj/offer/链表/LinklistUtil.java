package com.zj.offer.链表;

/**
 * @author junzhou
 * @date 2021/3/10 21:04
 * @description:TODO
 * @since 1.8
 */
public class LinklistUtil {


    public static void printLinklistNode(ListNode head){
        System.out.println();
        ListNode temp = head;
        while (temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 创建一个单链表
     * @param nums
     * @return
     */
    public static ListNode create(int[] nums){
        if (nums.length == 0) {
            return null;
        }

        ListNode head = new ListNode(nums[0]);
        ListNode tempHead = head;

        if (nums.length>1){
            for (int i = 1; i < nums.length; i++) {
                ListNode next = new ListNode(nums[i]);
                tempHead.next = next;
                tempHead = next;
            }
        }
        return head;
    }

}
