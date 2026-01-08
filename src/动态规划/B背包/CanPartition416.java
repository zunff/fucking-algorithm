package 动态规划.B背包;

import java.util.Arrays;

/**
 * 416. 分割等和子集  中等  2025/12/29
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition416 {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1};
        System.out.println(new CanPartition416().canPartitionThird(nums));
    }


    /**
     * 定义 nums 总和为 sum
     *
     * 将题目转换为，找几个数，使得他们之和为 sum / 2，即 01 背包问题
     * 无解条件：sum % 2 != 0
     *
     * dp[i][j] 为 前 i个数，是否能找出几个数相加结果为 j
     * 初始化：dp[0][0] = true
     *      i > 0 dp[i][0] = true   一个都不选
     *      j > 0 dp[0][j] = false
     * {
     *     j >= nums[i]     dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
     *     j < nums[i]      dp[i][j] = dp[i - 1][j]
     * }
     */
    public boolean canPartitionThird(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int v = sum / 2;
        boolean[] dp = new boolean[v + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];

            }
        }
        return dp[v];


//        int n = nums.length;
//        int sum = Arrays.stream(nums).sum();
//        if (sum % 2 != 0) {
//            return false;
//        }
//        int v = sum / 2;
//        boolean[][] dp = new boolean[n + 1][v + 1];
//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = true;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= v; j++) {
//                dp[i][j] = dp[i - 1][j];
//                if (j >= nums[i - 1]) {
//                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
//                }
//            }
//        }
//        return dp[n][v];
    }




































    /**
     * 问题等价于 选出 几个数，使得他们之和等于 sum / 2
     * 变成了01背包，容量为 sum / 2
     * dp[i][j] 表示前i个数，能否凑够容量 j
     * 边界：dp[0][0] = true
     *      dp[i][0] = true    可以凑够0，一个都不选就行
     *      dp[0][j] = false
     * 条件：sum / 2 能整除才有解
     * {
     *     j >= nums[i]    dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j]     选或不选
     *     j < nums[i]     dp[i][j] = dp[i - 1][j]                              只能不选
     * }
     */
    public boolean canPartitionSecond(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int v = sum / 2;
        boolean[][] dp = new boolean[n + 1][v + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 1; j <= v; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j - num] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][v];
    }























    /**
     * 转化为 0 1 背包，容量为 sum / 2
     * dp[i][j]  表示前i个数字，是否能组成 j
     * 边界：dp[0][0] = true
     *      i = 0 时  没有数字不能组成 j，false
     *      j = 0 时  数字可以不选 能组成0，true
     * {
     *     j >= v：dp[i][j] = dp[i - 1][j - v] || dp[i - 1][j]
     *     j < v： dp[i][j] = dp[i - 1][j]
     * }
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int v = sum / 2;
        int n = nums.length;
        boolean[] dp = new boolean[v + 1];
        dp[0] = true;

        // 注意 01背包 是内层倒序
        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= 1; j--) {
                int num = nums[i - 1];
                if (j >= num) {
                    dp[j] = dp[j - num] || dp[j];
                }
            }
        }
        return dp[v];
    }
}
