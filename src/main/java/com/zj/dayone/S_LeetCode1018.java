package com.zj.dayone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junzhou
 * @date 2021/1/14 22:10
 * @description:TODO
 * @since 1.8
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * 示例 2：
 *
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * 示例 3：
 *
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * 示例 4：
 *
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 30000
 * A[i] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S_LeetCode1018 {

    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> result = new ArrayList<Boolean>();
        int data = 0;

        if (A[0] == 0){
            result.add(true);
        }else {
            data = 1;
            result.add(false);
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i] == 1){
                data += Math.pow(2, i-1);
                System.out.println("A["+i+"] = " + A[i] + "  data: " + data);
            }
            if (data % 5 == 0){
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(prefixesDivBy5(new int[]{1,1,1,0,1}));
    }
}
