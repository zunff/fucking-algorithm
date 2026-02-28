package 动态规划.F综合;

/**
 * 1567. 乘积为正数的最长子数组长度 中等
 *
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 *
 * 示例  1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 *
 * 示例 2：
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MaxLengthSubarrayWithPositiveProduct1567 {
    public static void main(String[] args) {
        System.out.println(new MaxLengthSubarrayWithPositiveProduct1567().getMaxLen(new int[]{-1,-2,-3,0,1}));
    }

    public int getMaxLenSecond(int[] nums) {
        return 0;
    }































































    /**
     * 考虑最后一个数 i，如果是负数，则结果变为 s[0...i]中乘积为负数的最长子数组长度 + 1
     *                 如果是正数，则结果变为 s[0...i]中乘积为正数的最长子数组长度 + 1
     * pos[i] 表示前 i 个数中，以 nums[i] 结尾的子数组中，乘积为 正 数的最长子数组的长度
     * neg[i] 表示前 i 个数中，以 nums[i] 结尾的子数组中，乘积为 负 数的最长子数组的长度
     * 初始化：
     * {
     *     nums[0] == 0     pos[0] = 0        neg[0] = 0
     *     nums[0] > 0      pos[0] = 1        neg[0] = 0
     *     nums[0] < 0      pos[0] = 0        neg[0] = 1
     * }
     *
     *
     * {
     *     nums[i] == 0     pos[i] = 0                      neg[i] = [0]
     *     nums[i] > 0      pos[i] = pos[i - 1] + 1         neg[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0
     *     nums[i] < 0      neg[i] = pos[i - 1] + 1         pos[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0
     * }
     *
     * ans = max(pos[i])
     */
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int pos = 0;
        int neg = 0;

        if (nums[0] > 0) {
            pos = 1;
        } else if (nums[0] < 0) {
            neg = 1;
        }
        int res = pos;
        for (int i = 1; i < n; i++) {
            if(nums[i] > 0) {
                pos = pos + 1;
                neg = neg >  0 ? neg + 1 : 0;
            } else if (nums[i] < 0) {
                int lastNeg = neg;
                neg = pos + 1;
                pos = lastNeg > 0 ? lastNeg + 1 : 0;
            } else {
                pos = 0;
                neg = 0;
            }
            res = Math.max(res, pos);
        }
        return res;
    }
}
