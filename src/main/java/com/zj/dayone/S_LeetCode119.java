package com.zj.dayone;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoujun
 * @Date: 2021/2/12 14:46
 * @Description:
 */
public class S_LeetCode119 {

    /**
     * 每行数字左右对称，由 11 开始逐渐变大再变小，并最终回到 11。
     *
     * 第 nn 行（从 00 开始编号）的数字有 n+1n+1 项，前 nn 行共有 \frac{n(n+1)}{2}
     * 2
     * n(n+1)
     * ​
     *   个数。
     *
     * 第 nn 行的第 mm 个数（从 00 开始编号）可表示为可以被表示为组合数 \mathcal{C}(n,m)C(n,m)，记作 \mathcal{C}_n^mC
     * n
     * m
     *   或 {m}(
     * m
     * n
     * ​
     *  )，即为从 nn 个不同元素中取 mm 个元素的组合数。我们可以用公式来表示它：C_n^m=\dfrac{n!}{m!(n-m)!}C
     * n
     * m
     *  =
     * m!(n−m)!
     * n!
     *
     *
     * 每个数字等于上一行的左右两个数字之和，可用此性质写出整个杨辉三角。即第 nn 行的第 ii 个数等于第 n-1n−1 行的第 i-1i−1 个数和第 ii 个数之和。这也是组合数的性质之一，即 \mathcal{C}_n^i=\mathcal{C}_{n-1}^i+\mathcal{C}_{n-1}^{i-1}C
     * n
     * i
     * ​
     *  =C
     * n−1
     * i
     * ​
     *  +C
     * n−1
     * i−1
     * ​
     *  。
     *
     * (a+b)^n(a+b)
     * n
     *   的展开式（二项式展开）中的各项系数依次对应杨辉三角的第 nn 行中的每一项。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
                }
            }
            C.add(row);
        }
        return C.get(rowIndex);
    }

    public static void main(String[] args) {
        S_LeetCode119 let = new S_LeetCode119();

        System.out.println(let.getRow(30));
    }
}
