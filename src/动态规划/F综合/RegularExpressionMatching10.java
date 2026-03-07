package 动态规划.F综合;

/**
 * 10. 正则表达式匹配 困难
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 返回一个布尔值，表示匹配是否覆盖整个输入字符串（而非部分）。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 提示：
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class RegularExpressionMatching10 {
    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching10().isMatchSecond("aa", ".*"));
    }


    public boolean isMatchThird(String s, String p) {
        return false;
    }



























































    /**
     * 1...n
     *
     * dp[i][j] 表示 s 的前 i 个字符，和 p 的前 j 个字符，是否适配
     * 初始化：dp[0][0] = true
     *        dp[i][0] = false
     *        dp[0][j] = j > 2 && p[j - 1] == '*' && dp[0][j - 2]
     * {
     *     if p[j - 1] == '*' :
     *          if s[i - 1] == p[j - 2] || p[j - 2] == '.':
     *              dp[i][j] = dp[i - 1][j] || dp[i][j - 2]     // dp[i - 1][j]表示*代表多个，dp[i][j - 2]表示*代表0个
     *          else :
     *              dp[i][j] = dp[i][j - 2]                     // 只能代表 0 个
     *     else :
     *          if s[i - 1] == p[j - 1] || p[j - 1] == '.' :
     *              dp[i][j] = dp[i - 1][j - 1]
     *          else :
     *              dp[i][j] = false
     * }
     */
    public boolean isMatchSecond(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 2; j <= m; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[n][m];
    }






























































    /**
     * 1 ... n
     *
     * 考虑最后一个字符，s[n - 1] 和 p[m - 1]
     *
     * if p[m - 1] == '*'
     *      if p[m - 2] == s[n - 1] || p[m - 2] == '.'
     *          res = s(0 ... n-2) 和 p(0 ... m-1) 是否匹配 || s(0 ... n-1) 和 p(0 ... m-3)是否匹配
     *      else
     *          res = s(0 ... n-1) 和 p(0 ... m-3)是否匹配
     * else
     *      if s[n - 1] == p[m - 1] || p[m - 1] == '.'
     *          res = s(0 ... n-2) 和 p(0 ... m-2) 是否匹配
     *      else
     *          res = false
     *
     *
     * dp[i][j] 表示前 s 的前i个字符 和 p 的前 j个字符是否匹配
     * 初始化：dp[0][0] = true
     *        dp[i][0] = false
     *        dp[0][j] =  j >= 2 && p[j - 1] == * && dp[0][j - 2]
     * {
     * if p[j - 1] == '*'
     *      if p[j - 2] == s[i - 1] || p[j - 2] == '.'
     *          dp[i][j] = dp[i - 1][j] || dp[i][j - 2]
     *      else
     *          dp[i][j] = dp[i][j - 2]
     * else
     *      if s[i - 1] == p[j - 1] || p[j - 1] == '.'
     *          dp[i][j] = dp[i - 1][j - 1]
     *      else
     *          dp[i][j] = false
     * }
     */
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 2; j <= m; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' &&  dp[0][j - 2];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[n][m];
    }
}











