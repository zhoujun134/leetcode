package com.zj.offer.二叉树;

public class 求二叉树中的最大路径和leetcode124 {
    /***
     * 求⼆叉树中最⼤路径和
     */
    int ans = 0;
    public int oneSideMax(TreeNode root){
        if(root == null ) {
            return 0;
        }
        int left = Math.max(0, oneSideMax(root.left));
        int right = Math.max(0, oneSideMax(root.right));
        ans = Math.max(ans, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 10;
        TreeNode left = new TreeNode();
        left.val = 9;
        TreeNode rigth = new TreeNode();
        rigth.val = 2;

        root.left = left;
        root.right = rigth;

        TreeNode ll = new TreeNode();
        ll.val = 5;
        TreeNode lr = new TreeNode();
        lr.val = 56;

        left.left = ll;
        left.right = lr;

        TreeNode lll = new TreeNode();
        lll.val = 15;
        TreeNode llr = new TreeNode();
        llr.val = 36;

        ll.left = lll;
        ll.right = llr;

        求二叉树中的最大路径和leetcode124 leetcode124 = new 求二叉树中的最大路径和leetcode124();

        System.out.println(" --> "+ leetcode124.oneSideMax(root));
    }

}

