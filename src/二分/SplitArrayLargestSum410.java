package 二分;

/**
 * 410. 分割数组的最大值 困难
 *
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。
 * 返回分割后最小的和的最大值。
 * 子数组 是数组中连续的部分。
 *
 * 示例 1：
 * 输入：nums = [7,2,5,10,8], k = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：9
 *
 * 示例 3：
 * 输入：nums = [1,4,4], k = 3
 * 输出：4
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum410 {
    public static void main(String[] args) {
        System.out.println(new SplitArrayLargestSum410().splitArray(new int[]{7,2,5,10,8}, 2));
    }

    public int splitArray(int[] nums, int k) {
        return 0;
    }































































    /**
     * 题意：把数组切成 k 段，让每段的和都 ≤ M，并且希望这个 M 尽量小
     * 等价于：存在一个最小的 M，使得每段都可以在和不超过 M 的条件下，分成 <= k组的子数组
     * 为什么是 <= k，因为 nums[i] 非负数，如果 3 组可以，要求的是 5 组，那完全可以把任一个子数组拆分到最终 5 组
     *
     * 二分的范围，也是 M 的范围
     * 上界：所有元素放在同一段，sum(nums)
     * 下界：每个元素一段，max(nums)
     *
     * 怎么判断一个 M 是否符合题意？
     * 贪心，因为数组是连续的，每一段都尽量多的元素，只有当这一段的和 > M 了，才开新段，最终看段数是否 <= k
     */
    public int splitArray_First(int[] nums, int k) {
        int l = 0;
        int r = 0;
        for (int n : nums) {
            l = Math.max(l, n);
            r += n;
        }

        int ans = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (can_First(mid, nums, k)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public boolean can_First(int M, int[] nums, int k) {
        int count = 1;
        int curSum = 0;
        for (int n : nums) {
            if (curSum + n > M) {
                count++;
                curSum = n;
                continue;
            }
            curSum += n;
        }
        return count <= k;
    }
}
