package 动态规划.hot100;

/**
 * 279. 完全平方数 中等
 *
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 104
 */
public class NumSquares279 {
    public static void main(String[] args) {
        System.out.println(new NumSquares279().numSquares(12));
    }

    /**
     * 完全背包，物品为小于等于n的所有完全平方数，容量为n
     * dp[i][j] 表示前 i 个完全平方数中，在 j 容量下最少需要多少个完全平方数能凑成n
     * 初始化：dp[0][0] = 0、dp[0][j] = INF、dp[i][0] = 0
     *
     * {
     *     j >= nums[i]     dp[i][j] = min(dp[i - 1][j], dp[i][j - nums[i]] + 1)
     *     j < nums[i]      dp[i][j] = dp[i - 1][j]
     * }
     */
    public int numSquares(int n) {
        int size = (int)Math.sqrt(n);
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = (int)Math.pow(i + 1, 2);
        }
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[size + 1][n + 1];
        for (int i = 0; i <= size; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = INF;
            }
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - nums[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[size][n];
    }
}
