package com.zj.dayone;

/**
 * @author junzhou
 * @date 2021/1/8 21:49
 * @description:
 * 给定一个数组，将数组中的元素向右移动个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 1.8
 */
public class M_LeetCode189 {

    public static  void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            rotateOne(nums);
        }
    }

    /**
     * 移动依次
     * 数组的元素依次向后移动依次
     * @param nums
     */
    public static void rotateOne(int[] nums){
        int end_data = nums[nums.length-1];
        int index = 1;
        for (int i = nums.length-1; i > 0; i--){
            nums[i] = nums[i-1];
        }
        nums[0] = end_data;
    }

    /**
     * 方法二 使用额外的数组
     *
     * @param nums
     * @param k
     */
    private static void method2(int[] nums, int k){
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 方法三
     * 方法二中使用额外数组的原因在于如果我们直接将每个数字放至它最后的位置，这样被放置位置的元素会被覆盖从而丢失。因此，从另一个角度，我们可以将被替换的元素保存在变量 \textit{temp}temp 中，从而避免了额外数组的开销
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     */
    public static void method3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }


    /**
     * 方法 4
     * 先反转全部数组，在反转前k个，最后在反转剩余的，如下所示
     * @param nums
     * @param k
     */
    public static void method4(int[] nums, int k){
        int length = nums.length;
        k %= length;
        //先反转全部的元素
        method4Reverse(nums, 0, length - 1);
        //在反转前k个元素
        method4Reverse(nums, 0, k - 1);
        //接着反转剩余的
        method4Reverse(nums, k, length - 1);
    }

    public static void method4Reverse(int[] nums, int start, int end){
        int temp = nums[start];
        while (start <= end){
            temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
    static void printArr(int[] nums){
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+ " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
//        int a = 12;
        int[] a = {-1,-100,3,99};
        rotate(a, 2);
        printArr(a);

//        System.out.println(11%12);
    }


}
