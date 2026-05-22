package 动态规划.hot100;

/**
 * 5. 最长回文子串 中等
 *
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome5 {

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome5().longestPalindrome("cbbd"));
    }

    /**
     * dp[i][j] 表示 s[i..j] 是不是回文子串
     * init: dp[0][0] = true
     *       dp[i][i] = true
     * {
     *     s[i] == s[j]     dp[i][j] = j - i < 2 || dp[i + 1][j - 1]
     *     s[i] != s[j]     dp[i][j] = false
     * }
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][i] = true;
        }
        int start = 1;
        int end = 1;
        int maxLen = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = j - i < 2 || dp[i + 1][j - 1];
                    if (dp[i][j]) {
                        int curLen = j - i + 1;
                        if (curLen > maxLen) {
                            maxLen = curLen;
                            start = i;
                            end = j;
                        }
                    }
                }
            }
        }
        return s.substring(start - 1, end);
    }
}
