package com.zj.offer.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你二叉搜索树的根节点 root ，中的两个节该树点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 恢复二叉搜索树leetcode99 {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(-1);

        root = createBSTree();

        System.out.println("修正之前的树为: ");
        TreeNodeUtils.middleTraverse(root);
        System.out.println();
        recoverTree1(root);
        System.out.println("修正之后的树为: ");
        TreeNodeUtils.middleTraverse(root);


    }

    /***
     * 解题方法一
     * 假设有一个递增序列 a=[1,2,3,4,5,6,7]。
     * 如果我们交换两个不相邻的数字，例如 2 和 6，原序列变成了 a=[1,6,3,4,5,2,7]，
     * 那么显然序列中有两个位置不满足 a_i< a_{i+1}，
     * 在这个序列中体现为 6>3，5>2，因此只要我们找到这两个位置，
     * 即可找到被错误交换的两个节点。如果我们交换两个相邻的数字，例如 2 和 3，
     * 此时交换后的序列只有一个位置不满足 a_i<a_{i+1}。因此整个值序列中不满足条件的位置或者有两个，或者有一个。
     *
     * 至此，解题方法已经呼之欲出了：
     * +++： 找到二叉搜索树中序遍历得到值序列的不满足条件的位置。
     * +++：如果有两个，我们记为 i 和 j（i<j & a_i>a_{i+1} & a_j>a_{j+1}。那么对应被错误交换的节点即为 a_i，
     *      对应的节点和 a_{j+1} 对应的节点，我们分别记为 x 和 y。
     * +++：如果有一个，我们记为 i，那么对应被错误交换的节点即为 a_i，对应的节点和 a_{i+1} 对应的节点，我们分别记为 x 和 y。
     * +++：交换 x 和 y两个节点即可
     *
     * 实现部分，本方法开辟一个新数组 nums 来记录中序遍历得到的值序列，然后线性遍历找到两个位置 i 和 j，
     * 并重新遍历原二叉搜索树修改对应节点的值完成修复。
     *
     * @param root
     */
    public static void recoverTree1(TreeNode root) {
        List<Integer> nums = new ArrayList<Integer>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    public static void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public static int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);
                if (x == -1) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    public static void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }


    /***
     * 测试 树
     * @return
     */
    public static TreeNode createBSTree(){

        TreeNode root = new TreeNode(4);

        TreeNode l = new TreeNode(6);
        root.left = l;
        TreeNode r = new TreeNode(2);
        root.right = r;
        TreeNode ll = new TreeNode(1);
        l.left = ll;
        TreeNode lr = new TreeNode(3);
        l.right = lr;
        TreeNode rl = new TreeNode(5);
        r.left = rl;
        TreeNode rr = new TreeNode(7);
        r.right = rr;
        return root;
    }


    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

}
