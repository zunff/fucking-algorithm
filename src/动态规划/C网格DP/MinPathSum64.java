package 动态规划.C网格DP;

/**
 * 64. 最小路径和  中等
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 * 输入：grid = [
 *              [1,3,1],
 *              [1,5,1],
 *              [4,2,1]
 * ]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class MinPathSum64 {
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new MinPathSum64().minPathSum(grid));
    }

    /**
     * 0 ... base - 1
     *
     * dp[i][j] 表示从左上角到grid[i][j] 路径上的最小总和
     * 初始化：dp[0][0] = grid[0][0]
     *        dp[i][0] = dp[i - 1][0] + grid[i][0]          由于只能来自于 上 和 左，j = 0时只能来自于上
     *        dp[0][j] = dp[0][j - 1] + grid[0][j]          i = 0时只能来自于左
     * {
     *     dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
     * }
     */
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            // 更新 dp[0] 只能从上面来 这里没办法跟二维一样提前在外面初始化好dp[i][0]
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1];



//        int n = grid.length;
//        int m = grid[0].length;
//        int[][] dp = new int[n][m];
//        dp[0][0] = grid[0][0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = dp[i - 1][0] + grid[i][0];
//        }
//        for (int j = 1; j < m; j++) {
//            dp[0][j] = dp[0][j - 1] + grid[0][j];
//        }
//
//        for (int i = 1; i < n; i++) {
//            for (int j = 1; j < m; j++) {
//                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
//            }
//        }
//        return dp[n - 1][m - 1];
    }
}










