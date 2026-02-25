package 动态规划.E区间DP;

import java.util.Arrays;

/**
 * 132. 分割回文串 II 困难
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 *
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 *
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class PalindromePartitioningII132 {
    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningII132().minCut("aacbab"));
    }

    /**
     * aacbab -> aa | c | bab
     *
     * 考虑最后一刀，枚举最后一段回文子串的起点 j，最后一刀切在 j-1 | j 之间（当 j>0），右边一定是一个回文子串，那么最后的结果就是看 s[0..j - 1]他要多少刀能使所有子串都是回文串，发现是最小子问题
     * 预处理 p[i][j] 表示 s[i..j] 是不是回文子串、按长度遍历
     * {
     *     s[i] == s[j]     p[i][j] = j - i <= 2 || p[i + 1][j - 1]
     *     s[i] != s[j]     p[i][j] = false
     * }
     *
     * dp[i] 表示 s[0..i]最少需要多少刀能使所有子串都是回文串，其中有一个切点j使得s[j..i]为回文串
     * 如果 p[0][i] == true 那么直接 dp[i] = 0
     * {
     *     p[j][i] == true     dp[i] = min(dp[i], dp[j - 1] + 1)
     * }
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            p[i][i] = true;
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                char a = s.charAt(i);
                char b = s.charAt(j);
                if (a == b) {
                    p[i][j] = j - i <= 2 || p[i + 1][j - 1];
                }
            }
        }

        int INF = 2001;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (p[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 1; j <= i; j++) {
                if (p[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
