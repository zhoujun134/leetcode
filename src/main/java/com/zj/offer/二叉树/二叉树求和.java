package com.zj.offer.二叉树;

/**
 * @Author: zhoujun
 * @Date: 2021/3/14 10:16
 * @Description:
 */
public class 二叉树求和 {

    public static int sum(TreeNode root){
        if (root == null) {
            return 0;
        }
        return root.val +
                sum(root.left) +
                sum(root.right);
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6,7};
        TreeNode root = TreeNodeUtils.randomCreateOneTree();
//        System.out.println(root);
        // 前序遍历
        TreeNodeUtils.prefaceTraverse(root);
//        int[] sum = new int[1];
        int sum = sum(root);
        System.out.println();
        System.out.println("sum = "+ sum);
    }

}
