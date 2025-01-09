package com.zj.offer.二叉树;

import java.util.*;

/**
 * @Author: zhoujun
 * @Date: 2021/3/25 11:22
 * @Description:
 * 使用到的思想就是使用队列，先进先出的特性
 *
 */
public class 之字形打印二叉树 {

    /**
     * 使用到的思想就是使用队列，先进先出的特性
     * 从前序排序的思想: 根左右
     *  从根节点开始，左右节点分别入队，打印每层的数据，
     * @param pRoot
     * @return
     */
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
            if (!list.isEmpty()) {
                ret.add(list);
            }
        }
        return ret;
    }
}
