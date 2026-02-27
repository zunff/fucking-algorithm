package 动态规划.F综合;

/**
 * 279. 完全平方数 中等
 *
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 104
 */
public class PerfectSquares279 {

    public static void main(String[] args) {
        System.out.println(new PerfectSquares279().numSquares(13));
    }

    /**
     *  先预处理出 所有 < n 的完全平方数 nums
     *  问题转化为了 从 nums 中 最少取多少个 num 使得和为 n
     *
     *  dp[i][j] 表示在前 i 个 nums中，j的容量下，最少选多少个 num 可以使得和为 j
     *  边界：   dp[0][0] = 0
     *          dp[0][j] = INF
     *          dp[i][0] = 0
     *  其他未提及的dp初始化为INF
     *  {
     *      j >= nums[i]    dp[i][j] = min(dp[i][j - nums[i]] + 1, dp[i - 1][j])
     *      j < nums[i]     dp[i][j] = dp[i - 1][j]
     *  }
     */
    public int numSquares(int n) {
        int INF = 110;
        int size = (int) Math.sqrt(n);
        int[] nums = new int[size];
        for (int i = 1; i <= size; i++) {
            nums[i - 1] = (int) Math.pow(i, 2);
        }

        int[] dp = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            dp[j] = INF;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = nums[i - 1]; j <= n; j++) {
                dp[j] = Math.min(dp[j - nums[i - 1]] + 1, dp[j]);
            }
        }

        return dp[n];



//        int INF = 110;
//        int size = (int) Math.sqrt(n);
//        int[] nums = new int[size];
//        for (int i = 1; i <= size; i++) {
//            nums[i - 1] = (int) Math.pow(i, 2);
//        }
//
//        int[][] dp = new int[size + 1][n + 1];
//        for (int i = 0; i <= size; i++) {
//            for (int j = 1; j <= n; j++) {
//                dp[i][j] = INF;
//            }
//        }
//
//        for (int i = 1; i <= size; i++) {
//            for (int j = 1; j <= n; j++) {
//                int num = nums[i - 1];
//                if (j >= num) {
//                    dp[i][j] = Math.min(dp[i][j - num] + 1, dp[i - 1][j]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }
//
//        return dp[size][n];
    }
}
