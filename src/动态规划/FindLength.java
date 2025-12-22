package 动态规划;

/**
 * 718. 最长重复子数组 中等
 *
 * 提示
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 *
 * 示例 2：
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 *
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class FindLength {
    public static void main(String[] args) {
        int[] nums1 = {0,0,0,0,1};
        int[] nums2 = {1,0,0,0,0};
        System.out.println(new FindLength().findLength(nums1, nums2));
    }

    /**
     * 与 1143 很类似，但状态定义不一样，连续的子数组用这种定义
     * 方便下一个状态拼接时，如果nums1[i-1] nums2[j-1]不相同，直接将其视为0，因为不连续了
     *
     * 定义：dp[i][j] 为 nums1 以 i 结尾 且 nums2 以 j 结尾的 最长重复子数组长度
     * 那么有
     * {
     *     nums1[i]  = nums2[j]：  dp[i][j] = dp[i-1][j-1] + 1
     *     nums1[i] != nums2[j]：  dp[i][j] = 0
     * }
     * 结果为 所有 dp[i][j] 的最大值
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;

        for (int i = 1; i <= m; i++) {
            int a = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int b = nums2[j - 1];
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(dp[i][j], res);

                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }
}
