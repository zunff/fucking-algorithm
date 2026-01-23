package 动态规划.C网格DP;

/**
 * 221. 最大正方形 中等
 *
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],
 *                ["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare221 {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(new MaximalSquare221().maximalSquareThird(matrix));
    }

    /**
     * 0..base-1
     *
     * dp[i][j] 表示 以 matrix[i][j] 为右下角的只包含'1'的正方形的最大边长是多少
     * init：dp[i][0] = matrix[i][0] == '1' ? 1 : 0
     *       dp[0][j] = matrix[0][j] == '1' ? 1 : 0
     * {
     *     matrix[i][j] == '1'  dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1
     *     matrix[i][j] == '0'  dp[i][j] = 0
     * }
     */
    public int maximalSquareThird(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxLen = Math.max(maxLen, dp[i][0]);
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            maxLen = Math.max(maxLen, dp[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }
























































    /**
     * 0...base-1
     *
     * dp[i][j] 表示以matrix[i][j] 为右下角的 只包含 '1' 的最大正方形的 边长长度
     * 初始化：dp[i][0] = matrix[i][0] == '1' ? 1 : 0
     *        dp[0][j] = matrix[0][j] == '1' ? 1 : 0
     * {
     *     matrix[i][j] == '1'    dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
     *     matrix[i][j] == '0'    dp[i][j] = 0
     * }
     *
     * ans = max(dp[i][j]) * max(dp[i][j])
     */
    public int maximalSquareSecond(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            res = Math.max(res, dp[i][0]);
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            res = Math.max(res, dp[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res * res;
    }
























































    /**
     * 定义 dp[i][j] 为以 matrix[i][j] 作为右下角的 只包含 '1' 的最大正方形的边长
     * 初始化: dp[0][0] = matrix[0][0] == '1' ? 1 : 0
     *        dp[i][0] = matrix[i][0] == '1' ? 1 : 0
     *        dp[0][j] = matrix[0][j] == '1' ? 1 : 0
     * {
     *      matrix[i][j] == '1'     dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
     *      matrix[i][j] != '1'     dp[i][j] = 0
     * }
     *
     * 答案，max(dp[i][j])  * max(dp[i][j])
     */
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int res = dp[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            res = Math.max(res, dp[i][0]);
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            res = Math.max(res, dp[0][j]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res  * res;
    }
}
