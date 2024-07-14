package com.zj.offer.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 15:51
 * @Description:
 */
public class 二叉树的层次遍历 {

    public static void main(String[] args) {
        TreeNode testTree = TreeNodeUtils.createTestTree();
        levelOrder(testTree).forEach(System.out::println);
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Queue<TreeNode> quee = new ArrayDeque<>();
        quee.add(root);
        while (!quee.isEmpty()) {
            int size = quee.size();
            ArrayList<Integer> resOne = new ArrayList<>();
            while (size-- != 0) {
                TreeNode temp = quee.poll();
                if (temp != null){
                    resOne.add(temp.val);
                }
                if (temp != null && temp.left != null) {
                    quee.add(temp.left);
                }
                if (temp != null && temp.right != null) {
                    quee.add(temp.right);
                }
            }
            /**
             * git a ds a da  da
             * this is zj1
             */
            if (resOne.size() > 0) {
                result.add(resOne);
            }
            /**
             * this is zj11 test
             */
        }
        return result;
    }



    public static ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root != null){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                int size = queue.size();
                //遍历的时候加入结果集合也是可以的，而且更简便
                ArrayList<Integer> newLevel = new ArrayList<Integer>();
                for(int i=0; i<size; i++){
                    TreeNode temp = queue.poll();
                    newLevel.add(temp.val);
                    if(temp.left != null)
                        queue.offer(temp.left);
                    if(temp.right != null)
                        queue.offer(temp.right);
                }
                result.add(newLevel);
            }
        }
        return result;
    }
}
