package com.zj.offer.二叉树;

import java.util.*;

/**
 * @Author: zhoujun
 * @Date: 2021/3/25 11:22
 * @Description:
 */
public class 之字形打印二叉树 {

    public List<List<Integer>> printByZ(TreeNode pRoot) {
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            if (list.isEmpty()) {
                ret.add(list);
            }
        }
        return ret;
    }
}
