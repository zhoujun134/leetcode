package test.test;

import com.zj.datastruct.sort.QuickSort;

/**
 *
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("============   start  ====================");

        System.out.println(getMaxCha(new int[]{3, 6, 9, 1}));

        System.out.println("============    end           =============");
    }

    public static int getMaxCha(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        QuickSort.quickSort(arr, 0, arr.length - 1);
        int maxCha = 0;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i] - arr[i - 1];
            if (temp > maxCha) {
                maxCha = temp;
            }
        }
        return maxCha;
    }
}  
