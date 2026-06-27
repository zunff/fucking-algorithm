package 动态规划;

/**
 * 53. 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
public class MaxSubArray53 {

    public static void main(String[] args) {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {-1,-2};
        int[] nums = {5,4,-1,7,8};
        System.out.println(new MaxSubArray53().maxSubArray_Second(nums));
    }

    /**
     * dp[i] 表示以nums[i]结尾的最大子数组和
     * ans 取最大的dp[i]
     *
     * {
     *     dp[i] = max(dp[i - 1] + nums[i - 1], nums[i - 1])
     * }
     */
    public int maxSubArray_Second(int[] nums) {
        int ans = nums[0];
        int n = nums.length;
        int dp = 0;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            int sum = dp + num;
            dp = Math.max(sum, num);
            ans = Math.max(ans, dp);
        }
        return ans;
    }


























































    public int maxSubArray(int[] nums) {
        int m = nums.length;
        if (m == 1) {
            return nums[0];
        }
        int[] dp = new int[m];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < m; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
