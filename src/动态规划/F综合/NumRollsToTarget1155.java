package 动态规划.F综合;

/**
 * 1155. 掷骰子等于目标和的方法数 中等
 *
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n、k 和 target，请返回投掷骰子的所有可能得到的结果（共有 kn 种方式），使得骰子面朝上的数字总和等于 target。
 * 由于答案可能很大，你需要对 10^9 + 7 取模。
 *
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你掷了一个有 6 个面的骰子。
 * 得到总和为 3 的结果的方式只有一种。
 *
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你掷了两个骰子，每个骰子有 6 个面。
 * 有 6 种方式得到总和为 7 的结果: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1。
 *
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须对 109 + 7 取模。
 *
 *
 * 提示：
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class NumRollsToTarget1155 {

    public static void main(String[] args) {
        System.out.println(new NumRollsToTarget1155().numRollsToTarget(30, 30, 500));
    }


    /**
     * 考虑最后一步，假设最后一个骰子 z 面朝上(1 <= j <= k)，那么子问题就变成了 前 n - 1 个骰子，总合为 target - z 有几种方式，是最小子问题
     * z 需要遍历 k，考虑每一种情况
     *
     * dp[i][j] 表示 前 i个 骰子，有几种组合方式之和为 j，定义 1 <= z <= k
     * 初始化：dp[1][1] = 1
     *        dp[1][j] = j <= k ? 1 : 0
     *        dp[i][1] = 0
     * {
     *     dp[i][j] = z sum(dp[i - 1][j - z])
     * }
     */
    public int numRollsToTarget(int n, int k, int target) {

        int MOD = 1000000007;

        int[][] dp = new int[n + 1][target + 1];

        dp[1][1] = 1;
        for (int j = 1; j <= target; j++) {
            dp[1][j] = j <= k ? 1 : 0;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= target; j++) {
                for (int z = 1; z <= k && j - z >= 0; z++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - z]) % MOD;
                }
            }
        }

        return dp[n][target];
    }
}
