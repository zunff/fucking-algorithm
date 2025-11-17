package 动态规划;

/**
 * 416. 分割等和子集    中等
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
public class CanPartition {
    public static void main(String[] args) {
//        int[] nums = {1,5,11,5};
        int[] nums = {1,2,5};
        System.out.println(new CanPartition().canPartition(nums));
    }

    /**
     * 转化为 01 背包
     * nums 求和得到 sum, 总容量 = sum / 2, 如果除不尽则无解
     *
     * dp[i][j] 的定义为：前 i 个 数字下，容量为 j 时，是否存在一个子集，使得子集之和=总容量，即 sum /2
     * 原因：因为sum / 2 一定除得尽，所以如果有一个子集之和为 sum/2 ，那么另一个子集一定等于 sum/2
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 不能整除2一定无解
        if (sum % 2 != 0) {
            return false;
        }
        int v = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][v + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][v];
    }
}
