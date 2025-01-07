package com.zj.offer.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreeNodeUtils {

    public static TreeNode randomCreateOneTree() {
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
        return root;
    }

    /**
     * 非递归实现前序遍历
     */
    public static ArrayList<Integer> prefaceTraverse2(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            ret.add(temp.val);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return ret;
    }

    /**
     * 非递归实现中序遍历
     */
    public static List<Integer> middleTraverse2(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (true) {
            if (!stack1.isEmpty()) {
                TreeNode temp = stack1.pop();
                if (temp.left != null) {
                    stack1.push(temp.left);
                }
                stack2.push(temp);
            } else if (!stack2.isEmpty()) {
                TreeNode temp = stack2.pop();
                ret.add(temp.val);
                if (temp.right != null) {
                    stack1.push(temp.right);
                }
            } else {
                break;
            }
        }
        return ret;
    }

    public static List<Integer> middleTraverse3(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> headerList = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (cur !=null || !headerList.isEmpty()) {
            while (cur != null) {
                headerList.push(cur);
                cur = cur.left;
            }
            cur = headerList.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    public static void prefaceTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        prefaceTraverse(root.left);
        prefaceTraverse(root.right);
    }

    public static void middleTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        middleTraverse(root.left);
        System.out.print(root.val + " ");
        middleTraverse(root.right);
    }

    /**
     * 非递归实现后续遍历
     */
    public static ArrayList<Integer> afterTraverse2(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode parent = stack1.pop();
            if (null != parent.right) {
                stack1.push(parent.right);
            }
            if (null != parent.left) {
                stack1.push(parent.left);
            }
            stack2.push(parent);
        }

        while (!stack2.isEmpty()) {
            TreeNode parent = stack2.pop();
            ret.add(parent.val);
        }
        return ret;
    }

    public static void afterTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        afterTraverse(root.left);
        afterTraverse(root.right);
        System.out.print(root.val + " ");
    }


    /***
     * 判断是否是一棵 二叉搜索树
     * @param root 树的根节点
     * @return
     */
    public static void isBSTree(TreeNode root, TreeNode pre) {


    }

    /**
     * 根据有序数组创建 二叉搜索树
     *
     * @param midleArr 有序数组
     * @return 树节点
     */
    public static TreeNode buildBSTree(int[] midleArr) {
        TreeNode root = null;
        for (int j : midleArr) {
            if (j == -1) {
                continue;
            }
            buildBiTree(root, j);
        }

        return root;
    }

    /**
     * 描述:
     * 构建二叉树
     * TreeNode 为结点,
     * data 为数据
     */
    private static void buildBiTree(TreeNode root, int data) {
        //如果根结点是空,那么设置根结点,并且设置数据域
        if (root == null) {
            root = new TreeNode(data);
        } else {
            /**
             * 根结点不为空,那么判断数据是否小于当前结点的数据
             */
            if (data < root.getVal()) {
                //如果小于,判断当前结点是否有左叶子结点
                if (root.getLeft() == null) {
                    //左叶子结点为空,设置左叶子结点,并且设置数据
                    root.setLeft(new TreeNode(data));
                } else {
                    //左叶子结点不为空,递归调用构建二叉树的函数
                    buildBiTree(root.getLeft(), data);
                }
            } else {
                //如果大于或等于,判断当前结点是否存在右叶子结点
                if (root.getRight() == null) {
                    //右叶子结点为空,设置右叶子结点,并且设置数据域
                    root.setRight(new TreeNode(data));
                } else {
                    //右叶子几点不为空,递归调用构建二叉树的函数
                    buildBiTree(root.getRight(), data);
                }
            }
        }
    }

    /**
     * Morris 遍历 前序遍历
     *
     * @param head 根节点
     */
    public static void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if (mostRight != null) {
                // cur有左孩子，找到cur左子树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    // mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * Morris 遍历 中序遍历
     *
     * @param head 根节点
     */
    public static void morrisIn(TreeNode head) {
        if (head == null) {
            return;
        }

        TreeNode cur = head;
        TreeNode mostRight = null;

        while (cur != null) {

            mostRight = cur.left;
            if (mostRight != null) {

                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }

            }
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    /**
     * Morris 遍历 后遍历
     *
     * @param head 根节点
     */
    public static void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(TreeNode node) {
        TreeNode tail = reverseEdge(node);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode node) {
        TreeNode pre = null;
        TreeNode next = null;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 根据数组创建一颗二叉树
     */
    public static TreeNode createTreeNode(int[] array, int index) {
        TreeNode root = null;
        if (index < array.length) {
            int value = array[index];
            if (value == -1){
                return root;
            }
            root = new TreeNode(value);
            root.left = createTreeNode(array, 2 * index + 1);
            root.right = createTreeNode(array, 2 * index + 2);
            return root;
        }
        return root;
    }

    public static TreeNode createTestTree() {

        TreeNode root = new TreeNode(1);

        TreeNode l = new TreeNode(2);
        root.left = l;
        TreeNode r = new TreeNode(3);
        root.right = r;
        TreeNode ll = new TreeNode(4);
        l.left = ll;
        TreeNode lr = new TreeNode(5);
        l.right = lr;
        TreeNode rl = new TreeNode(6);
        r.left = rl;
        TreeNode rr = new TreeNode(7);
        r.right = rr;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createTestTree();
        System.out.println("前序遍历===> ");
        prefaceTraverse(root);
        System.out.println();
        System.out.println("中序遍历===> ");
        middleTraverse(root);
        System.out.println();
        middleTraverse3(root).forEach(System.out::println);
        System.out.println("后序遍历===> ");
        afterTraverse(root);
        System.out.println();
        // afterTraverse2(root).forEach(System.out::println);
    }
}
