package 动态规划.A二维序列DP;

/**
 * 516. 最长回文子序列 中等 2025/12/24 60min
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 *
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class LongestPalindromeSubseq516 {

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(new LongestPalindromeSubseq516().longestPalindromeSubseqSecond(s));
    }




    public int longestPalindromeSubseqThird(String s) {
        return 0;
    }






























    /**
     * dp[i][j] 为 s[i...j]的最长回文子序列长度   2025/12/25 20min
     * {
     *     s[i] == s[j]   dp[i][j] = dp[i+1][j-1] + 2
     *     s[i] != s[j]   dp[i][j] = max(dp[i][j-1], dp[i + 1][j])
     * }
     */
    public int longestPalindromeSubseqSecond(String s) {
        int n = s.length();
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n; i >= 1; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[1][n];
    }
































    /**
     * 状态定义：dp[i][j] 表示 s[i...j] 的最长回文子序列长度
     * 状态转移方程：
     * {
     *     s[i] == s[j]：     dp[i][j] = dp[i + 1][j - 1] + 2
     *     s[i] != s[j]：     dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
     * }
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[1][n];
    }

}
