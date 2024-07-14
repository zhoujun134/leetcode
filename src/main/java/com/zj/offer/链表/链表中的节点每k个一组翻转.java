//package com.zj.offer.链表;
//
//import java.util.Stack;
///**
// * @Author: zhoujun
// * @Date: 2021/3/23 20:58
// * @Description:
// */
//public class 链表中的节点每k个一组翻转 {
//    public static void main(String[] args) {
//        int[] nums = {1};
//        ListNode head = LinklistUtil.create(nums);
//
//        ListNode newHead = reverseKGroup(head, 2);
//
//        LinklistUtil.printLinklistNode(newHead);
//
//
//    }
//
//    /**
//     * 解法1：栈
//     * 把 k个数压入栈中，然后弹出来的顺序就是翻转的！
//     * 这里要注意几个问题：
//     * 第一，剩下的链表个数够不够 k 个（因为不够 k个不用翻转）；
//     * 第二，已经翻转的部分要与剩下链表连接起来。
//     */
//    public static ListNode reverseKGroup(ListNode head, int k) {
//        // 存储反转节点的栈
//        Stack<ListNode> stack = new Stack<>();
//        // 返回的重点节点
//        ListNode ret = new ListNode(-1);
//        // 定义反转的指针
//        ListNode p = ret;
//        while (true) {
//            // 记录反转的数量
//            // 初始指针
//            int count = 0;
//            // 将当前的节点入栈
//            ListNode tmp = head;
//            while (tmp != null && count < k) {
//                stack.push(tmp);
//                tmp = tmp.next;
//                count++;
//            }
//            // 如果栈内的元素不足以反转的 k 个元素
//            if (count != k) {
//                // 将 p 指针直接接上剩下的节点
//                p.next = head;
//                break;
//            }// 反转链表
//            while (!stack.isEmpty()) {
//                p.next = stack.pop();
//                p = p.next;
//            }// 重置下一次的初始位置
//            p.next = tmp;
//            head = tmp;
//        }
//        return ret.next;
//    }
//
//    private static ListNode reverseByKGroup(ListNode head, int k) {
//        // 定义一个栈
//        ArrayLinkedList<ListNode> stack = new ArrayLinkedList<>();
//        // 结果节点
//        ListNode result = new ListNode(-1);
//
//        ListNode p = result;
//        while (true){
//            // 记录节点数量
//            int count = 0;
//            ListNode tmp = head;
//            // 入栈
//            while(tmp != null && count < k){
//                stack.addLast(tmp);
//                tmp = tmp.next;
//                count++;
//            }
//            // 如果还有剩余，翻转并退出循环
//            if (count != k){
//                while(!stack.isEmpty()){
//                    p.next = stack.removeLast();
//                    p = p.next;
//                }
//                p.next = tmp;
//                break;
//            }
//            // k 个一组 翻转
//            while(!stack.isEmpty()){
//                p.next = stack.removeLast();
//                p = p.next;
//            }
//            // 设置下一个位置
//            p.next = tmp;
//            head = tmp;
//
//        }
//        return result.next;
//    }
//}
