package com.zj.offer.链表;

/**
 * @author junzhou
 * @date 2021/3/10 21:03
 * @description:TODO
 * @since 1.8
 */
public class 反转单链表 {


    public static ListNode convert(ListNode head) {
        //定义一个带头节点的
        ListNode resultList = new ListNode(-1);
        //循环节点
        ListNode p = head;
        while (p != null) {
            //保存插入点之后的数据
            ListNode tempList = p.next;
            p.next = resultList.next;
            resultList.next = p;
            p = tempList;
        }
        return resultList.next;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        ListNode head = LinklistUtil.create(data);

        LinklistUtil.printLinklistNode(head);

        head = convert(head);

        LinklistUtil.printLinklistNode(head);

    }
}
