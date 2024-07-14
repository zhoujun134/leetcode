package com.zj.offer.二叉树;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-08-09
 * <p>
 * 描述
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 * 注：本题保证二叉树中每个节点的val值均不相同。
 * 示例1
 * 输入：
 * [3,5,1,6,2,0,8,#,#,7,4],5,1
 * 返回值：3
 */
public class 在二叉树中找到两个节点的最近公共祖先 {

    public static void main(String[] args) {
        int[] array = {3, 5, 1, 6, 2, 0, 8, -1, -1, 7, 4};
        TreeNode root = TreeNodeUtils.createTreeNode(array, 0);
        System.out.println("前序遍历");
        TreeNodeUtils.prefaceTraverse(root);
        System.out.println();
        System.out.println("中序遍历");
        TreeNodeUtils.middleTraverse(root);
        System.out.println("===> " + getSameTreeNode(root, 6, 8).val);

    }

    public static TreeNode getSameTreeNode(TreeNode root, int o1, int o2) {
        // 超过叶子节点，或者root为p、q中的一个直接返回
        if (root == null || root.val == o1 || root.val == o2) {
            return root;
        }
        // 返回左侧的p\q节点
        TreeNode left = getSameTreeNode(root.left, o1, o2);
        // 返回右侧的p\q节点
        TreeNode right = getSameTreeNode(root.right, o1, o2);

        if (left == null) { // 都在右侧
            return right;
        }
        if (right == null) { // 都在左侧
            return left;
        }
        return root; // 在左右两侧
    }

}
