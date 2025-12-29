package 动态规划.A二维序列DP;

/**
 * 583. 两个字符串的删除操作 中等 2022/12/27
 *
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 *
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class DeleteOperationForTwoStrings583 {

    public static void main(String[] args) {
        String word1 = "leetcode";
        String word2 = "etco";
        System.out.println(new DeleteOperationForTwoStrings583().minDistance(word1, word2));
    }



    public int minDistanceSecond(String word1, String word2) {
        return 0;
    }
























    /**
     * dp[i][j] 表示 word1 前i个字符 和 word2 前j个字符 使得两个字符相同所需最小步数
     * 边界：当 i = 0 时，即word1为空串，那么 dp[0][j] = j，即word2字符的长度，表示word2删完就等于 word1了
     *       当 j = 0 时，同理，dp[i][0] = i
     * {
     *     word1[i] == word2[j]   dp[i][j] = dp[i-1][j-1]                           ps：相等的话，就不用删了，看两个字符串都-1字符需要多少步能变一样
     *     word1[i] != word2[j]   dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1        ps：不相同，先带上这个字符，再减去这个字符 word1[i] 或 word2[j]
     * }
     * PS：这题也能转换成 1143. 最长公共子序列
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        // 初始化数据
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
