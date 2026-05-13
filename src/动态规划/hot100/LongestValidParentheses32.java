package 动态规划.hot100;

/**
 * 32. 最长有效括号 困难
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
 * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class LongestValidParentheses32 {
    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses32().longestValidParentheses("())"));
    }

    /**
     * dp[i] 表示前i个字符中 以s[i] 结尾的有效括号子串长度
     * {
     *     s[i] == '('                            dp[i] = 0
     *     s[i] == ')' && s[i - 1] == '('         dp[i] = i > 2 ? dp[i - 2] + 2 : 2
     *     s[i] == ')' && s[i - 1] == ')'         {
     *         j = i - dp[i - 1] - 1;
     *         s[j] == '('                        dp[i] = dp[i - 1] + 2 + (j - 1 >= 0 ? dp[j - 1] : 0)
     *     }
     * }
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                char lastC = s.charAt(i - 1);
                if (lastC == '(') {
                    dp[i] = i > 2 ? dp[i - 2] + 2 : 2;
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0) {
                        char jc = s.charAt(j);
                        if (jc == '(') {
                            dp[i] = dp[i - 1] + 2 + (j - 1 > 0 ? dp[j - 1] : 0);
                        }
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}




























