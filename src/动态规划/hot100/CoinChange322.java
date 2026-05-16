package 动态规划.hot100;

/**
 * 322. 零钱兑换 中等
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange322 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new CoinChange322().coinChange(coins, 11));
    }

    /**
     * 完全背包
     * dp[i][j] 表示前 i 个硬币，在 j 总金额下凑成总金额所需的 最少的硬币个数
     * 初始化：dp[0][0] = 0
     *        dp[0][j] = INF
     *        dp[i][0] = 0
     * {
     *     j >= coins[i]    dp[i][j] = min(dp[i - 1][j], dp[i][j - coins[i]] + 1)
     *     j < coin[i]      dp[i][j] = dp[i - 1][j]
     * }
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        int INF = Integer.MAX_VALUE / 2;
        for (int j = 1; j <= amount; j++) {
            dp[j] = INF;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);

            }
        }
        return dp[amount] >= INF ? -1 : dp[amount];
    }
}
