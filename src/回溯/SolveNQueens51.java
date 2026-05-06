package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后 困难
 *
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],
 *      ["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 * 提示：
 * 1 <= n <= 9
 */
public class SolveNQueens51 {
    public static void main(String[] args) {
        System.out.println(new SolveNQueens51().solveNQueens(4));
    }

    boolean[] col;
    boolean[] diag1;
    boolean[] diag2;
    char[][] board;

    List<List<String>> result = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backTracking(n, 0);
        return result;
    }

    private void backTracking(int n, int row) {
        if (n == row) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
                ans.add(sb.toString());
            }
            result.add(ans);
        }

        for (int i = 0; i < n; i++) {
            int d1 = i - row + n - 1;
            int d2 = i + row;

            // 判断当前row 的每一个位置是否可以放皇后
            if (col[i] || diag1[d1] || diag2[d2]) {
                continue;
            }
            // 当前位置可以放
            col[i] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            board[row][i] = 'Q';
            backTracking(n, row + 1);
            col[i] = false;
            diag1[d1] = false;
            diag2[d2] = false;
            board[row][i] = '.';
        }
    }

}
