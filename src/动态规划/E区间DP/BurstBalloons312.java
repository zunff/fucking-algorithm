package 动态规划.E区间DP;

/**
 * 312. 戳气球 困难
 *
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 */
public class BurstBalloons312 {

    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        System.out.println(new BurstBalloons312().maxCoins(nums));
    }

    /**
     * dp[i][j] 表示 在开区间 (i,j) 内戳破所有气球的最大硬币数量
     * k 是这个区间内最后被搓破的气球
     * {
     *     dp[i][j] = safeNums[k] * safeNums[i] * safeNums[j] + dp[i][k] + dp[k][j]
     * }
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int safeN = n + 2;
        int[] safeNums = new int[safeN];
        safeNums[0] = 1;
        safeNums[safeNums.length - 1] = 1;
        for (int i = 0; i < n; i++) {
            safeNums[i + 1] = nums[i];
        }

        int[][] dp = new int[safeN][safeN];

        for (int len = 2; len < safeN; len++) {
            for (int i = 0; i < safeN - len; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(safeNums[i] * safeNums[k] * safeNums[j] + dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }

        return dp[0][safeN - 1];
    }
}
















