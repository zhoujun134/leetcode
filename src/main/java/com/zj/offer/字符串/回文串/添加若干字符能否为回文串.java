package com.zj.offer.字符串.回文串;
import java.util.Scanner;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-08-15
 */
public class 添加若干字符能否为回文串 {
    private static int MAXSIZE = 500;
    private static int d[][] = new int[MAXSIZE][MAXSIZE];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        int n = chars.length;
        System.out.println(toPalindrome(chars, n));

    }

    static int toPalindrome(char[] s, int n) {
        int i, j, k;
        //只有一个字符时，不需要添加字符
        for (i = 0; i < n; i++) {
            d[i][i] = 0;
        }
        //串长度为2时
        for (i = 1; i < n; i++) {
            if (s[i - 1] == s[i]) {
                d[i - 1][i] = 0;
            } else {
                d[i - 1][i] = 1;
            }
        }

        //串长度递增
        for (k = 2; k < n; k++) {
            for (i = 0, j = k; j < n; i++, j++) {
                if (s[i] == s[j]) {
                    d[i][j] = d[i + 1][j - 1];
                } else {
                    d[i][j] = Math.min(1 + d[i][j - 1], d[i + 1][j]) + 1;
                }
            }
        }
        return d[0][n - 1];
    }


    public int minInsertions(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] strChars = s.toCharArray();
        int N = s.length();
        int[][] dp = new int[N][N];
        //一条斜线一条斜线的填，斜线从右下到左上，斜线整体从左到右
        for (int row = N - 2; row >= 0; row--) {
            //定位一条斜线
            int tempRow = row;
            for (int col = N-1; col >=N-row-1 ; col--) {
                dp[tempRow][col] = strChars[tempRow] == strChars[col] ?
                                   dp[tempRow + 1][col - 1] : 1 + Math.min(dp[tempRow + 1][col],dp[tempRow][col-1]);
                tempRow--;
            }
        }
        /**
         * 直接将补全的字符串打印出来
         */
        char[] res = new char[N+dp[0][N - 1]];
        int resl=0,resr=res.length-1;//两个写指针
        int sl=0,sr=N-1;//两个读字符串的指针
        while (sl<=sr){
            if(strChars[sl]==strChars[sr]){
                res[resl++] = strChars[sl++];
                res[resr--] = strChars[sr--];
            }else if(dp[sl][sr-1]<dp[sl+1][sr]){
                res[resl++] = strChars[sr];//在左边补上右边那个字符
                res[resr--] = strChars[sr--];
            }else{
                res[resr--] = strChars[sl];
                res[resl++] = strChars[sl++];
            }
        }
        System.out.println(String.valueOf(res));
        /*--------------------------------------*/
        return dp[0][s.length() - 1];
    }
}
