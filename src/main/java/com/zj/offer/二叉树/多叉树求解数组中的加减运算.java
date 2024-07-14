package com.zj.offer.二叉树;

import java.util.Scanner;

import lombok.Data;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-09-19
 */
public class 多叉树求解数组中的加减运算 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int target = in.nextInt();
        String binaryStr = Integer.toBinaryString(target);
        int[] nums = new int[binaryStr.length() + 1];
        for (int i = 0; i < binaryStr.length() + 1; i++){
            nums[i] = (int) Math.pow(2, i);
        }
        //创建的数的深度为给定数组的长度加一
        TraceBackTree bt = new TraceBackTree(new Node(1, 1),
                nums.length + 1, nums, target);
        bt.backTrace();
    }
}

/**
 * 给定一个整数数组int[] a (a.length > 1)，和一个整数值
 * 试输出所有运算结果等于m的运算过程。可使用的运算方式只有加法和减法。
 * 数组元素最多参与一次运算。
 * 例如，给定数组【5,4,6,7,1】和整数9,
 * 输出运算结果为9的运算过程如下：
 * +5+4=9
 * +5+4+6-7+1=9
 * +5+4-6+7-1=9
 * +5-4+7+1=9
 * +4+6-1=9
 * -4+6+7=9
 * -5+6+7+1=9
 * #############################################################
 * 这个题目，我们可以使用回溯算法得到所有的解。回溯法在问题的解空间树中，按深度优先策略，
 * 从根节点出发搜索解空间树。算法搜索至解空间树中的任一节点时，先判断该节点是否包含问题的解。
 *        如果不包含，则跳过对已该结点为根的子数的搜索，逐层向其祖先结点回溯。
 *        否则，进入该子树，继续按深度优先策略搜索。
 * 回溯法求问题所有解时，要回溯到根，且根结点的所有子树都已被搜索遍才结束。
 * 回溯法求问题的一个解时，只要搜索到问题的一个解就可结束。
 *
 * 回溯法通常包含3个步骤
 *      1) 针对所给问题，定义问题的解空间
 *      2) 确定易于搜索的解空间结构。常见的结构一般为n叉树结构，而且一般都是满n叉树。
 *      3) 以深度优先方式搜索解空间，并在搜索过程中使用剪枝函数避免无效搜索。深度优先策略可以选择先序遍历，中序遍历，和后序遍历。
 *
 * 对于给定的这个题目，我们首先要确定问题的解空间。由于如下的条件限定
 *      1) 运算过程只能使用加法和减法
 *      2) 数组元素最多参与一次运算
 * 我们可以把数组元素的操作转换为 (x1 * +1 ) + (x2 * -1) + (x3 * 0) ....... = ?
 * 以题目为例, 很容易看出题目需要的解向量为 { (1,1,1,-1,1), (-1,0,1,1,1)..... } ,
 * 然后我们可以确定出解空间结构为一个3叉树，而且是一个满三叉树。三叉树深度是给定数组的长度加一，
 * 参考链接：https://blog.csdn.net/john548/article/details/52217143
 * ##############################################################
 */
@Data
class TraceBackTree {

    private Node root;
    private int depth;
    private int[] numArr;
    private int target;

    public TraceBackTree(Node root, int depth, int[] numArr, int target) {
        this.root = root;
        this.depth = depth;
        buildBTree();
        this.numArr = numArr;
        this.target = target;
    }

    /*
     * 构建解空间数据结构，题目所需要的是一个满三叉树。
     */
    private void buildBTree() {
        this.root.setLeft(createNode(1, 2));
        this.root.setMiddle(createNode(0, 2));
        this.root.setRight(createNode(-1, 2));

    }
    // 递归创建一颗三叉树
    private Node createNode(int data, int depth) {
        if (depth <= this.depth) {
            Node tempNode = new Node(data, depth);
            tempNode.setLeft(createNode(1, depth + 1));
            tempNode.setMiddle(createNode(0, depth + 1));
            tempNode.setRight(createNode(-1, depth + 1));
            return tempNode;
        } else {
            return null;
        }
    }

    /*
     *回溯法求所有解。
     */
    public void backTrace() {
        // 定义存储解向量的数组。该数组长度与题目给定的数组长度相等。
        int[] x = new int[this.depth - 1];
        backTrace(this.root, x);

    }
    private void backTrace(Node curNode, int[] x) {

        if (curNode.getDepth() > 1) {
            // 将节点值付给解向量数组。Node 是从第二个节点开始存放 depth的，
            // 第二层 depth 为 1，
            // 第三层 depth 为 2， 依次类推
            x[curNode.getDepth() - 2] = curNode.getData();
        }

        if (constraints(x, curNode.getDepth() - 2)) {
            String res = getComputeSolution(x, curNode.getDepth() - 2);
            System.out.println(res);
        }
        if (curNode.getLeft() != null) {
            backTrace(curNode.getLeft(), x);
        }
        if (curNode.getMiddle() != null) {
            backTrace(curNode.getMiddle(), x);
        }
        if (curNode.getRight() != null) {
            backTrace(curNode.getRight(), x);
        }

    }

    /*
     * 检查目前解向量是否满足题目要求，就和等于指定值。
     */
    private boolean constraints(int[] x, int boundary) {
        int sum = 0;
        for (int i = 0; i <= boundary; i++) {
            sum += numArr[i] * x[i];
        }
        return (sum == target && x[boundary] != 0);
    }

    /**
     * 计算结果表达式
     * @param index 多项式表达式索引数组
     * @param boundary 索引的范围
     * @return 计算之后的表达式
     */
    private String getComputeSolution(int[] index, int boundary) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= boundary; i++) {
            if (index[i] == 1) {
                result.append("+").append(this.numArr[i]);
            }else if (index[i] == -1) {
                result.append("-").append(this.numArr[i]);
            }
        }

        return result.toString();
    }
}

/**
 * 构造树节点，包含左子节点，中子节点，和右子节点的引用。以及该节点深度及数据信息。
 */
@Data
class Node {
    // 三叉树的节点信息
    private Node left;
    private Node right;
    private Node middle;
    // 数据节点
    private int data;
    // 树的深度
    private int depth;

    public Node(int data, int depth){
        this.data = data;
        this.depth = depth;
    }
}