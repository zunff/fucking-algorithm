package 动态规划.hot100;

/**
 * 152. 乘积最大子数组 中等
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
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
public class MaxProduct152 {
    public static void main(String[] args) {
        System.out.println(new MaxProduct152().maxProduct(new int[]{0, 2}));
    }

    /**
     * max[i]、min[i]两种状态同时dp，表示前i个数中以nums[i]结尾的连续子数组中的乘积最大值和最小值
     *
     * {
     *     nums[i] == 0        max[i] = min[i] = 0
     *     nums[i] > 0         max[i] = max(nums[i] * max[i - 1], nums[i])        min[i] = min(nums[i] * min[i - 1], nums[i])  // 以nums[i]结尾，可以选择乘或不乘
     *     nums[i] < 0         max[i] = max(nums[i] * min[i - 1], nums[i])        min[i] = min(nums[i] * max[i - 1], nums[i])
     * }
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];

        int ans = max[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                max[i] = min[i] = 0;
            } else if (nums[i] > 0) {
                max[i] = Math.max(nums[i] * max[i - 1], nums[i]);
                min[i] = Math.min(nums[i] * min[i - 1], nums[i]);
            } else {
                max[i] = Math.max(nums[i] * min[i - 1], nums[i]);
                min[i] = Math.min(nums[i] * max[i - 1], nums[i]);
            }
            ans = Math.max(max[i], ans);
        }
        return ans;
    }
}
