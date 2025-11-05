package 动态规划;

import java.util.Arrays;

/**
 * 329. 矩阵中的最长递增路径
 *
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 * 示例 1：
 * 输入：matrix = [[9,9,4]
 *               ,[6,6,8]
 *               ,[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2：
 * 输入：matrix = [[3,4,5]
 *               ,[3,2,6]
 *               ,[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 */
public class LongestIncreasingPath {
    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        int res = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res = Math.max(traverse(matrix, i, j,  memo), res);
            }
        }
        System.out.println(res);
    }

    public static int traverse(int[][] matrix, int i, int j, int[][] memo) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int cur = matrix[i][j];

        int maxCount = 1;

        if (i - 1 >= 0 && cur < matrix[i - 1][j]) {
            maxCount = Math.max(maxCount, traverse(matrix, i - 1, j, memo) + 1);
        }
        if (i + 1 < m && cur < matrix[i + 1][j]) {
            maxCount = Math.max(maxCount, traverse(matrix, i + 1, j, memo) + 1);
        }
        if (j - 1 >= 0 && cur < matrix[i][j - 1]) {
            maxCount = Math.max(maxCount, traverse(matrix, i, j - 1, memo) + 1);
        }
        if (j + 1 < n && cur < matrix[i][j + 1]) {
            maxCount = Math.max(maxCount, traverse(matrix, i, j + 1, memo) + 1);
        }
        memo[i][j] = maxCount;
        return maxCount;
    }
}
