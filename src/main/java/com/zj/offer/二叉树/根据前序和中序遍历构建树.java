package com.zj.offer.二叉树;

/**
 * 前序遍历: 根左右
 * 中序遍历: 左根右
 * 那么。前序遍历的第一个节点，是这颗树的根节点，中序遍历中，根节点对应的值的左边是这个棵树的左子树的节点，右边的数为右子树的节点，
 * 同时，左子树的第一个节点又是第一个左子树的根节点，右子树的第一个节点是右边的根节点，
 * 类似的操作，依次遍历，
 * <a href="https://www.cnblogs.com/overxus/p/17200732.html">答案参考地址</a>
 */
public class 根据前序和中序遍历构建树 {

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = new TreeNode(pre[0]);
        buildTree(root, pre, 0, pre.length - 1, in,
                0, in.length - 1);
        return root;

    }

    public static void buildTree(TreeNode root, int[] pre, int pleft, int pright,
                                 int[] in, int ileft, int iright) {
        // 从中序遍历中找到根节点
        int i;
        for (i = ileft; i < iright; i++) {
            if (in[i] == root.val) {
                break;
            }
        }
        int t = i - ileft;
        // 子树长度为 0 时， 不生成子问题
        if (t > 0) {
            root.left = new TreeNode(pre[pleft]);
            buildTree(root.left, pre, pleft + 1,
                    pleft + 1 + t, in, ileft, i);
        }
        if (pright - pleft - 1 - t > 0) {
            root.right = new TreeNode(pre[pleft + t]);
            buildTree(root.right, pre, pleft + 1 + t,
                    pright, in, i + 1, iright);
        }
    }


    public static void main(String[] args) {
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        printTreddNode(treeNode);
    }

    static void printTreddNode(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreddNode(root.left);
        System.out.println(root.val);
        printTreddNode(root.right);
    }

}
