package com.zj.fresh.start.common;

import com.zj.fresh.start._01_链表反转_简单;
import lombok.Data;
import lombok.Getter;

/**
 * @ClassName ListNode
 * @Author zj
 * @Description
 * @Date 2025/3/13 23:53
 * @Version v1.0
 **/
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}
