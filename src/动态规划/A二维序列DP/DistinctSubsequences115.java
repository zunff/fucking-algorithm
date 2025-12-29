package 动态规划.A二维序列DP;

/**
 * 115. 不同的子序列  困难
 *
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 * 测试用例保证结果在 32 位有符号整数范围内。
 *
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 * 提示：
 * 1 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 */
public class DistinctSubsequences115 {
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(new DistinctSubsequences115().numDistinctSecond(s, t));
    }


    public int numDistinctThird(String s, String t) {
        return 0;
    }


























    /**
     * dp[i][j] 表示s的前i个字符的子序列中，t的前j个字符出现的个数
     * 边界：当 i = 0 时，s 是空串，空串无法得到 t
     *      当 j = 0 时，t 是空串，s 恒有一种方案得到空串子集
     *      dp[0][0] = 1
     * {
     *     s[i] == t[j]  dp[i][j] = dp[i-1][j-1] + dp[i - 1][j]   PS：相等的话，可以选择选或不选s[i]，组成t，由于字符相等，所以选的状态等价于dp[i - 1][j - 1]
     *     s[i] != t[j]  dp[i][j] = dp[i-1][j]                    PS：不相等，只能不选 s[i]，组成t
     * }
     */
    public int numDistinctSecond(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }





























    /**
     * dp[i][j] 表示 s中前i个字符 组成 t中前j个字符 有几种方案
     * {
     *     s[i] == t[j]     dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]            (选或不选最后一个字符)
     *     s[i] != t[j]     dp[i][j] = dp[i - 1][j]                               (只能不选最后一个字符)
     * }
     */
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return 0;
        }
        int[][] dp = new int[n + 1][m + 1];

        // 初始化：t 为空串时，方案数为 1
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a = s.charAt(i - 1);
                char b = t.charAt(j - 1);
                if (i < j) {
                    continue;
                }
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }
}
