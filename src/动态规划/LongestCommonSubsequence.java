package 动态规划;

/**
 * 1143. 最长公共子序列 中等
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence(text1, text2));
    }

    /**
     * 定义：0 <= i < text1.length     0 <= j < text2.length
     * dp[i][j]：text1 前 i 个字符，与 text2 前 j 个字符的最长公共子序列长度
     * 由于 i 和 j 的 1 表示第一个字符，但 text中0才是第一个字符，所以存在 1 的偏移
     * 当 当前的 i 和 j 对应的字符相等时，那么就考虑 text1[0, i -1] 和 text2[0, j - 1] 最长公共子序列 + 1
     * 当 不相等时，就从
     *                  第 i 跟 第 j 都不选：text1[0, i - 1] 和 text2[0, j - 1] 的最长公共子序列
     *                             选一个j：text1[0, i - 1] 和 text2[0, j]     的最长公共子序列
     *                            选一个i：text1[0, i]     和 text2[0, j - 1] 的最长公共子序列
     *             取一个最大值
     * 状态转移方程：
     * {
     *     text1[i - 1] = text2[j -1]：       dp[i][j] = dp[i - 1][j - 1] + 1
     *     test1[i - 1] != text2[j - 1]：     dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) = Math.max(dp[i - 1][j], dp[i][j - 1])
     * }
     *
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char a = text1.charAt(i - 1);
                char b = text2.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}

















