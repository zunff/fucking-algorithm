package 动态规划;

/**
 * 198. 打家劫舍  中等
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(new Rob().rob(nums));
    }

    /**
     * 当前位置 选或不选，选的话 那上一个必然来自于 n - 2，不选的话 那上一个必然来源于 n - 1
     */
    public int rob(int[] nums) {
        int m = nums.length;
        if (m == 1) {
            return nums[0];
        }
        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < m; i++) {
            int c = Math.max(a + nums[i], b);
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * 思路不太对，想着当前的位置一定选，然后往前推导，无非来源就是两个 n - 2、n - 3，选一个最大的就行
     */
    public int rob_1(int[] nums) {
        int m = nums.length;
        if (m == 1) {
            return nums[0];
        }
        if (m == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[m];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2] + nums[0];
        for (int i = 3; i < m; i++) {
            dp[i] = nums[i] + Math.max(dp[i - 2], dp[i - 3]);
        }
        return Math.max(dp[m - 1], dp[m - 2]);
    }
}
