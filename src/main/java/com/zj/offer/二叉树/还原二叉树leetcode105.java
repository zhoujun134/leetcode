package com.zj.offer.二叉树;

import java.util.HashMap;
import java.util.Map;

public class 还原二叉树leetcode105 {
    /***
     * 根据前序遍历和中序遍历的结果还原 ⼀棵⼆叉树
     * 假设树中没有重复的元素。
     */

    /**
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        int nodeNumber = 0;
        for(int i=0; i<inorder.length; i++){
            inMap.put(inorder[i], i);
            nodeNumber++;
        }
        TreeNode root = buildTree(preorder, 0,
                nodeNumber-1, inorder, 0, nodeNumber-1, inMap);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd,
                       Map<Integer, Integer> inMap){
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;
        root.left = buildTree(preorder, preStart + 1,
                preStart + numsLeft , inorder, inStart,
                inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1,
                preEnd, inorder, inRoot + 1, inEnd, inMap);
        return root;
    }


    public static void main(String[] args) {

        TreeNode root = TreeNodeUtils.randomCreateOneTree();
//        System.out.println("前序遍历");
//        Utils.prefaceTraverse(root);
//        System.out.println("中序遍历");
//        Utils.middleTraverse(root);

        int[] preorder = {10, 9, 5, 15, 36, 56, 2};
        int[] inorder = {15, 5, 36, 9, 56, 10, 2};
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        TreeNode root2 = new 还原二叉树leetcode105().buildTree(preorder, inorder);

        TreeNodeUtils.middleTraverse(root2);

    }
}