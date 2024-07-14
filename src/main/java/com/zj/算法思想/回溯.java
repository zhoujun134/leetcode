package com.zj.算法思想;

import java.util.Vector;

/**
 * @author junzhou
 * @date 2020/12/29 21:35
 * @description:TODO
 * @since 1.8
 */
public class 回溯 {

    /**
     * n 皇后问题
     */
    private Vector<Vector<Vector<String>>> res;

    Vector<Vector<Vector<String>>> solveNQueens(int n){
        // '.' 表⽰空，'Q' 表⽰皇后，初始化空棋盘。

        Vector<Vector<String>> board = new Vector<Vector<String>>();
        for (int i = 0; i < n; i++) {
            Vector<String> row = new Vector<String>();
            for (int j=0 ; j< n; j++){
                row.add(".");
            }
            board.add(row);
        }

        backtrack(board, 0);
        return res;
    }

    /**
     * 路径：board 中⼩于 row 的那些⾏都已经成功放置了皇后
     * 选择列表：第 row ⾏的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后⼀⾏
     * @param board
     * @param row
     */
    void backtrack(Vector<Vector<String>> board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            res.add(board);
            return;
        }
        int n = board.size();
        for (int col=0; col<n; col++){
            // 排除不合法选择
            if(!isValid(board, row, col)){
                continue;
            }
            // 做选择
            board.get(row).set(col, "Q");
            // 进行下一行决策
            backtrack(board, row+1);
            // 撤销选择
            board.get(row).set(col, ".");
        }

    }

    boolean isValid( Vector<Vector<String>>board, int row, int col){
        int n = board.size();
        // 检查列是否有皇后相互冲突
        for (int i = 0; i < n; i++) {
            if (board.get(i).get(col).equals("Q")){
                return false;
            }
        }
        // 检查右上方是否有皇后相互冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n ; i--, j++){
            if (board.get(i).get(j).equals("Q")){
                return false;
            }
        }
        // 检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0 ; i--, j--){
            if (board.get(i).get(j).equals("Q")){
                return false;
            }
        }

        return true;
    }
}
