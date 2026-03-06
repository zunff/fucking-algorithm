package 动态规划.F综合;

/**
 * 44. 通配符匹配  困难
 *
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2：
 * 输入：s = "aa", p = "*"
 * 输出：true
 * 解释：'*' 可以匹配任意字符串。
 *
 * 示例 3：
 * 输入：s = "cb", p = "?a"
 * 输出：false
 * 解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 *
 * 提示：
 * 0 <= s.length, p.length <= 2000
 * s 仅由小写英文字母组成
 * p 仅由小写英文字母、'?' 或 '*' 组成
 */
public class WildcardMatching44 {
    public static void main(String[] args) {
        System.out.println(new WildcardMatching44().isMatch("aabbbbbbbbbbbbbbbba", "aa*a"));
    }

    /**
     * 1...n
     *
     * dp[i][j] 表示 s 的前 i 个字符，和 p 的前 j 个字符 是否匹配
     * 初始化：dp[0][0] = true
     *        dp[i][0] = false
     *        dp[0][j] = dp[0][j - 1] && p[j - 1] == '*'
     * {
     *     if p[j - 1] == '*' :
     *          if j == 1 :
     *              dp[i][j] = true
     *          else :
     *              dp[i][j] = dp[i][j - 1]  || dp[i - 1][j]
     *     else :
     *          if p[j - 1] == s[i - 1] || p[j - 1] == '?' :
     *              dp[i][j] = dp[i - 1][j - 1]
     *          else
     *              dp[i][j] = false
     * }
     */
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '*') {
                    if (j == 1) {
                        dp[i][j] = true;
                    } else {
                        // dp[i][j - 1] 表示 * 匹配空串
                        // dp[i - 1][j] 表示 * 匹配任意一个字符
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                } else {
                    if (pc == sc || pc == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[n][m];
    }
}
