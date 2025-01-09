package com.zj.offer.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by com.zj
 * @Date: 2021/09/22 下午 10:59
 */
public class 二叉树根节点到叶子节点和为指定值的路径 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l = new TreeNode(4);
        TreeNode ll = new TreeNode(1);
        TreeNode lr = new TreeNode(11);
        TreeNode lll = new TreeNode(2);
        TreeNode llr = new TreeNode(7);
        TreeNode r = new TreeNode(8);
        TreeNode rr = new TreeNode(9);
        root.setLeft(l);
        root.setRight(r);
        l.setLeft(ll);
        l.setRight(lr);

        lr.setLeft(lll);
        lr.setRight(llr);

        r.setRight(rr);
        List<List<Integer>> lists = pathSum(root, 22);
        System.out.println("list: " + lists);


    }
    /**
     * @param root TreeNode类
     * @param sum int整型
     * @return int整型ArrayList<ArrayList<>>
     */
    public static List<List<Integer>> pathSum (TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathCompute(root, sum, new ArrayList<>(), result);
        return result;
        // write code here
    }

    public static void pathCompute(TreeNode root, int sum,
                                   List<Integer> path,
                                   List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                result.add(new ArrayList<>(path));
            }

        } else {
            pathCompute(root.left, sum, path, result);
            pathCompute(root.right, sum, path, result);
        }
        path.remove(path.size() - 1);
    }
}
