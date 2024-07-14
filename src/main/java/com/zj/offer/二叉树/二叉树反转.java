package com.zj.offer.二叉树;

/**
 * @author junzhou
 * @date 2021/3/14 12:46
 * @description:TODO
 * @since 1.8
 */
public class 二叉树反转 {

    public static void convert(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        convert(root.left);
        convert(root.right);
    }


    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.randomCreateOneTree();
        TreeNodeUtils.prefaceTraverse(root);
        convert(root);
        System.out.println("====================");
        TreeNodeUtils.prefaceTraverse(root);


    }
}
