package 动态规划;

/**
 * 152. 乘积最大子数组 中等
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */
public class MaxProduct {
    public static void main(String[] args) {
        int[] nums = {0,2};
        System.out.println(new MaxProduct().maxProduct(nums));
    }

    /**
     * 类似 53.最大子数组和
     * 但是如果定义一个dp表示以nums[i] 结尾的乘积最大子数组，那么最优子问题就不存在，因为子问题是否是最优解，由后续的子问题存不存在负数决定
     * 所以需要定义两个方程，一个表示以nums[i] 结尾的乘积最大子数组，一个表示以nums[i] 结尾的乘积最小子数组
     * 计算最大值时，需要拿出来上一个子问题的最小值，因为最小值可能为负数，负数乘以正数会变成正数
     *
     * 状态定义：
     * maxDp[i] 表示以 nums[i] 结尾的乘积最大子数组
     * minDp[i] 表示以 nums[i] 结尾的乘积最小子数组
     *
     * 状态转移方程：
     * maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], nums[i]), minDp[i - 1] * nums[i])
     * minDp[i] = Math.min(Math.min(minDp[i - 1] * nums[i], nums[i]), maxDp[i - 1] * nums[i])
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxDp = new int[n + 1];
        int[] minDp = new int[n + 1];

        maxDp[0] = 1;
        minDp[0] = 1;

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * num, num), minDp[i - 1] * num);
            minDp[i] = Math.min(Math.min(minDp[i - 1] * num, num), maxDp[i - 1] * num);
            res = Math.max(maxDp[i], res);
        }
        return res;
    }
}
