package 动态规划.B背包;

/**
 * 474. 一和零 中等
 *
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 *
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class FindMaxForm474 {

    public static void main(String[] args) {
//        String[] strs = {"10", "0001", "111001", "1", "0"};
        String[] strs = {"10", "0", "1"};
        System.out.println(new FindMaxForm474().findMaxFormSecond(strs, 1, 1));
    }

    public int findMaxFormThird(String[] strs, int m, int n) {
        return 0;
    }











































    /**
     * dp[i][j][k] 表示 前 i个字符，最多有 j 个 0 和 k 个 1 的 最大子集长度
     * 边界 dp[0][0][0] = 0
     *      dp[0][j][k] = 0
     *      dp[i][0][k] = 状态转移方程
     *      dp[i][j][0] = 状态转移方程
     * {
     *     cnt0[i] <= j && cnt1[i] <= k     dp[i][j][k] = max(dp[i - 1][j][k], dp[i - 1][j - cnt0[i]][k - cnt1[i]] + 1)
     *     cnt0[i] > j || cnt1[i] > k       dp[i][j][k] = dp[i - 1][j][k]
     * }
     *
     */
    public int findMaxFormSecond(String[] strs, int m, int n) {
        // 直接写优化掉i的数组
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int cnt0 = (int)strs[i - 1].chars().filter(ch -> ch == '0').count();
            int cnt1 = strs[i - 1].length() - cnt0;
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= cnt0 && k >= cnt1) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - cnt0][k - cnt1] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }










































    /**
     * dp[i][j][k] 为 0-i个字符串里，最多有 j个 0和 k个 1，最多能选几个字符
     * {
     *     cnt0[i] >= j && cnt1[i] >= i     dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-cnt0[i]][k-cnt1[j] + 1])
     *     cnt0[i] < j || cnt1[i] < i       dp[i][j][k] = dp[i-1][j][k]
     * }
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= l; i++) {
            String str = strs[i - 1];
            int length = str.length();
            int cnt0 = (int)str.chars().filter(ch -> ch == '0').count();
            int cnt1= length - cnt0;
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= cnt0 && k >= cnt1) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - cnt0][k -cnt1] + 1);
                    } else {
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }

        return dp[m][n];







//        int l = strs.length;
//        int[][][] dp = new int[l + 1][m + 1][n + 1];
//
//        for (int i = 1; i <= l; i++) {
//            String str = strs[i - 1];
//            int length = str.length();
//            int cnt0 = (int)str.chars().filter(ch -> ch == '0').count();
//            int cnt1= length - cnt0;
//            for (int j = 0; j <= m; j++) {
//                for (int k = 0; k <= n; k++) {
//                    if (j >= cnt0 && k >= cnt1) {
//                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - cnt0][k -cnt1] + 1);
//                    } else {
//                        dp[i][j][k] = dp[i - 1][j][k];
//                    }
//                }
//            }
//        }
//
//        return dp[l][m][n];
    }
}
