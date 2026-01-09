package 动态规划.B背包;

import java.util.Arrays;

/**
 494. 目标和 中等 2025/12/30

 给你一个非负整数数组 nums 和一个整数 target 。

 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

 示例 1：
 输入：nums = [1,1,1,1,1], target = 3
 输出：5
 解释：一共有 5 种方法让最终目标和为 3 。
 -1 + 1 + 1 + 1 + 1 = 3
 +1 - 1 + 1 + 1 + 1 = 3
 +1 + 1 - 1 + 1 + 1 = 3
 +1 + 1 + 1 - 1 + 1 = 3
 +1 + 1 + 1 + 1 - 1 = 3

 示例 2：
 输入：nums = [1], target = 1
 输出：1

 提示：
 1 <= nums.length <= 20
 0 <= nums[i] <= 1000
 0 <= sum(nums[i]) <= 1000
 -1000 <= target <= 1000
 */
public class FindTargetSumWays494 {


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(new FindTargetSumWays494().findTargetSumWaysThird(nums, 3));
    }

    /**
     * 0...base
     *
     * 分为两组 pos、neg
     * pos + neg = sum
     * pos - neg = target
     *
     * 2pos = sum + target     =>      pos = (sum + target) / 2
     *
     * 问题转化为 找几个数 组成pos，有多少种组合能组成 (sum + target) / 2，变成了01背包问题
     *
     * dp[i][j] 表示 前 i个数，有几种组合能组成 j
     * 边界：(sum + target) % 2 !== 0          0
     *      abs(target) > sum                 0
     * 初始化：dp[0][0] = 1
     *        dp[i][0] = dp[i - 1][0] + nums[i] == 0 ? 1 : 0   放到转移方程里循环即可
     *        dp[0][j] = 0; j > 0
     *
     * {
     *     j >= nums[i]     dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]
     *     j < nums[i]      dp[i][j] = dp[i - 1][j]
     * }
     */
    public int findTargetSumWaysThird(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }
        int n = nums.length;
        int v = (sum + target) / 2;
        int[] dp = new int[v + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = v; j >= 0; j--) {
                if (j >= num) {
                    dp[j] += dp[j - num];
                }
            }
        }
        return dp[v];
    }
































    /**
     * 子集和等于固定 target 的方案数
     *
     * pos + neg = sum
     * target  = pos - neg
     * 得到 pos = (target + sum) / 2
     * 转化为 01背包，选几个数，使得和为(target + sum) / 2，有几种方案，最大容量为(target + sum) / 2
     * dp[i][j] 表示在前i个数里，有几种方案能组成j
     * 初始化：dp[0][0] = 1  表示0个数有一种方案能组成j
     *        i = 0 时 dp[i][0] = 1 表示前 i 个数有一种方案能组成 0，一个都不选，但是如果nums[i]本身等于0，还得 + 1
     *        j = 0 时 dp[0][j] = 0 表示0个数无法组成j
     * {
     *     j >= nums[i]  dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]
     *     j < nums[i]   dp[i][j] = dp[i - 1][j]
     * }
     *
     */
    public int findTargetSumWaysSecond(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum || (target + sum) % 2 != 0) {
            return 0;
        }
        int v = (target + sum) / 2;
        int[][] dp = new int[n + 1][v + 1];
        dp[0][0] = 1;
        // 由于 nums[i - 1]有可能等于0，在初始化dp[i][0]时，如果nums[i - 1]=0，需要额外 + 1，所以直接放进循环里
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= v; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][v];
    }































    /**
     * 数学推导: 假设所有前面添加 '+'的整数之和为 pos、前面添加 '-'的整数之和为 neg
     * 则有：pos + neg = sum
     *      sum - 2neg = target
     *      neg = (sum - target) / 2
     * 所以把问题转化为了选几个数，把这几个数相加得到 neg，使得 (sum - target) / 2
     * 由于 neg 一定是整数、或者一个都不选，所以当且仅当 sum - target >= 0 且 (sum - target) / 2 除得尽，这题才有解
     *
     * 现在再看转化后的问题，变成了 01背包 ，容量是 (sum - target) / 2
     * dp[i][j] 表示 前i个数，有几种方案 将前i个数相加后组成j
     * 边界：dp[0][0] = 1，0个数组成0有一种方案
     *      i = 0 时 表示 0 前个数字组成 j 容量 dp[0][j] = 0
     *      j = 0 时 表示 i 前个数字成 0，可以一个都不选，所以 dp[i][0] = 1
     * {
     *     j >= nums[i]  dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j]       放得下选或不选
     *     j < nums[i]   dp[i][j] = dp[i - 1][j]                                放不下 只能不选
     * }
     */
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || (sum - target) % 2 != 0) {
            return 0;
        }
        int v = (sum - target) / 2;
        int[][] dp = new int[n + 1][v + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= v; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j - num] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][v];
    }
}
