package 动态规划.B背包;

import java.util.Arrays;

/**
 * 322. 零钱兑换  中等
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
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
     * dp[i][j] 表示前i个硬币，在容量j下的 最少硬币数是多少
     * 边界：dp[0][0] = dp[i][0] = 0  表示一个都不选
     *      dp[0][j] = Integer.MAX   表示无解
     * 初始化：所有格子都初始化为 无解 即 Integer.MAX
     * 如果子问题无解，那么dp[i][j]一定无解
     * {
     *     j >= coins[i]    dp[i][j] = min(dp[i - 1][j], dp[i][j - coins[i]] + 1)
     *     j < coins[i]     dp[i][j] = dp[i - 1][j]
     * }
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int INF = Integer.MAX_VALUE / 2; // 防止 +1 溢出，也更安全
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            for (int j = coin; j <= amount; j++) {
                // 选当前硬币
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] >= INF ? -1 : dp[amount];





//        int n = coins.length;
//        int INF = Integer.MAX_VALUE / 2; // 防止 +1 溢出，也更安全
//        int[][] dp = new int[n + 1][amount + 1];
//
//        // 初始化为无解
//        for (int i = 0; i <= n; i++) {
//            Arrays.fill(dp[i], INF);
//            dp[i][0] = 0; // 金额为 0 时都为 0
//        }
//
//        for (int i = 1; i <= n; i++) {
//            int coin = coins[i - 1];
//            for (int j = 1; j <= amount; j++) {
//                // 不选当前硬币
//                dp[i][j] = dp[i - 1][j];
//
//                // 选当前硬币
//                if (j >= coin) {
//                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coin] + 1);
//                }
//            }
//        }
//
//        return dp[n][amount] >= INF ? -1 : dp[n][amount];
    }
}
