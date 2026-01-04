package 动态规划.B背包;

/**
 * 1049. 最后一块石头的重量 II 中等   Day10
 *
 * 提示
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例 1：
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 示例 2：
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 *
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class LastStoneWeightII1049 {

    public static void main(String[] args) {
        int[] stones = {31,26,33,21,40};
        System.out.println(new LastStoneWeightII1049().lastStoneWeightII(stones));
    }



    public int lastStoneWeightIISecond(int[] stones) {
        return 0;
    }






























    /**
     * 类似于 494，但是 494 是求等于target，这题是求最小target
     * 还是有三个变量 pos neg sum，代表所有正数和、负数和、总和
     * ans = pos - neg
     * 由 sum = pos + neg 得到 pos - neg = sum - 2neg
     * 又因为 ans 一定大于等于0，所以要使得（pos - neg）最小，就是要（sum - 2neg）趋近于0，也就是让neg趋近于 sum/2
     *
     * 题目转换成了01背包，给定一堆石头重量，价值就是自己的重量，选出一些石头，使得它们的重量总和不超过 sum/2，并且价值尽量高
     *
     *
     * dp[i][j] 表示前i个石头，在最大容量j下的最大价值
     * 初始化：i = 0 前 0 个 石头 在 容量 j 下的最大价值 0
     *        j = 0 前 i 个 石头 在 容量 0 下的最大价值 0
     * {
     *      j >= stones[i]  dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i])
     *      j < stones[i]   dp[i][j] = dp[i - 1][j]
     * }
     * ans = sum - 2dp[n][v]
     */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // 除不尽的话就向下取整，我们这里求的是 “不超过 sum/2”
        int v = sum / 2;
        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                if (j >= stones[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return sum - 2 * dp[n][v];
    }
}
