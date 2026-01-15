package 动态规划.C网格DP;

/**
 * 63. 不同路径 II 中等
 *
 * 提示
 * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
 * 返回机器人能够到达右下角的不同路径数量。
 * 测试用例保证答案小于等于 2 * 109。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],
 *                      [0,1,0],
 *                      [0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *
 * 提示：
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class UniquePathsWithObstacles63 {

    public static void main(String[] args) {
        System.out.println(new UniquePathsWithObstacles63().uniquePathsWithObstaclesSecond(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }

    public int uniquePathsWithObstaclesThird(int[][] obstacleGrid) {
        return 0;
    }































































    /**
     * 0...base-1
     *
     * dp[i][j] 表示从左上角 到 grid[i][j] 不同的路径数量
     * 初始化：dp[0][0] = grid[0][0] == 1 ? 0 : 1
     *        dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0]
     *        dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1]
     * {
     *     grid[i][j] == 0      dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     *     grid[i][j] == 1      dp[i][j] = 0
     * }
     */
    public int uniquePathsWithObstaclesSecond(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[] dp = new int[m];

        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int j = 1; j < m; j++) {
            dp[j] = obstacleGrid[0][j] == 1 ? 0 : dp[j - 1];
        }
        for (int i = 1; i < n; i++) {
            dp[0] = obstacleGrid[i][0] == 1 ? 0 : dp[0];
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1];
                } else {
                    dp[j] = 0;
                }
            }
        }
        return dp[m - 1];



//        int n = obstacleGrid.length;
//        int m = obstacleGrid[0].length;
//        int[][] dp = new int[n][m];
//        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
//        }
//        for (int j = 1; j < m; j++) {
//            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
//        }
//
//        for (int i = 1; i < n; i++) {
//            for (int j = 1; j < m; j++) {
//                if (obstacleGrid[i][j] == 0) {
//                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//                }
//            }
//        }
//        return dp[n - 1][m - 1];
    }





























































    /**
     * 0...base-1
     *
     * dp[i][j] 表示从左上角 到 grid[i][j]  总共有多少条不同的路径
     * 初始化：dp[0][0] = grid[0][j] == 1 ? 0 : 1
     *        dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j-1]
     *        dp[i][0] = grid[i][0] == 1 ? 0 : dp[i-1][0]
     * {
     *     grid[i][j] == 1      dp[i][j] = 0
     *     grid[i][j] == 0      dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * }
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}













