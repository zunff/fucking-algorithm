package 动态规划;

/**
 * 494. 目标和 中等
 *
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        int targetSumWays = new FindTargetSumWays().findTargetSumWaysOpt(new int[]{1,1,1,1,1}, 3);
        System.out.println(targetSumWays);
    }


    /**
     * 动态规划 + 数学推导，转化为 01背包
     *
     * 设组成结果的所有正数之和为 pos，所有负数之和绝对值为 neg，所有数之和为sum，那么有：
     * 1. pos - neg = target
     * 2. pos + neg = sum
     * 两式相加得到：2pos = target + sum
     * 即 pos = (target + sum) / 2
     *
     * 所以把问题转化为了，选几个数，相加起来等于容量(target + sum) / 2，变成了经典01背包
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < Math.abs(target)) {
            return 0;
        }
        int n = nums.length;
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        int v = (target + sum) / 2;
        int[][] dp = new int[n + 1][v + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                // 选或不选，要用计数型DP，而不是最大值型DP，这种写法就变成了最多取多少个数
//                if (j - nums[i - 1] >= 0) {
//                    dp[i][j] = Math.max(dp[i - 1][j - nums[i - 1]] + 1, dp[i - 1][j]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
                // (不选当前i) + (选当前 i) 的所有方案之和
                // 不选当前i
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    // 选当前 i
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][v];
    }

    public int findTargetSumWaysOpt(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < Math.abs(target)) {
            return 0;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        int v = (target + sum) / 2;
        int[] dp = new int[v + 1];
        dp[0] = 1;

        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = v; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[v];
    }

    int[] nums;
    int target;
    Integer[][] memo;
    int offset;
    public int dfsFindTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        offset = sum;
        memo = new Integer[nums.length][2 * sum + 1];
        // 因为curSum可能为负数，所以用 (offset + curSum)表示[-sum, sum]的下标，[0, sum]表示curSum为负数时，[sum, 2*sum]表示curSum为正数时
        return dfs(0, 0);
    }

    public int dfs(int cursor, int curSum) {
        if (cursor == nums.length) {
            return curSum == target ? 1 : 0;
        }
        if (memo[cursor][offset + curSum] != null) {
            return memo[cursor][offset + curSum];
        }
        int n = nums[cursor];
        // 两种情况，加或减 分别 dfs
        memo[cursor][offset + curSum] = dfs(cursor + 1, curSum + n) + dfs(cursor + 1, curSum - n);
        return memo[cursor][offset + curSum];
    }
}
