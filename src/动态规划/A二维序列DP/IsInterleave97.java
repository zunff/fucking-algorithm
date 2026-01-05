package 动态规划.A二维序列DP;

/**
 * 97. 交错字符串 中等 2025/12/25
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 *
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 */
public class IsInterleave97 {
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(new IsInterleave97().isInterleaveThird(s1, s2, s3));
    }


    /**
     * dp[i][j] 表示 s1中前 i个字符 和 s2中前 j个字符，能否构成s3中前 i+j个字符
     * 边界：dp[0][0] = true 表示两个空串，可以构成一个空串
     *      i = 0：dp[i][0] = s1[i] == s3[i] && dp[i - 1][0]         表示 s1的前i个字符能否构成s3的前i个字符
     *      j = 0：dp[0][j] = s2[j] == s3[j] && dp[0][j - 1]         表示 s2的前j个字符能否构成s3的前j个字符
     * {
     *     s1[i] == s3[i + j]   dp[i][j] = dp[i - 1][j]
     *     s2[j] == s3[i + j]   dp[i][j] = dp[i][j - 1]
     *     s1[i] != s3[i + j] && s2[j] != s3[i + j]     false
     * }
     */
    public boolean isInterleaveThird(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if (n + m < s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1];
        }

        for (int i = 1; i <= n; i++) {
            char a = s1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char b = s2.charAt(j - 1);
                char c = s3.charAt(i + j - 1);
                if (a == c || b == c) {
                    dp[i][j] = a == c && dp[i - 1][j] || b == c && dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }









































    /**
     * 2025/12/26 第二次复写
     * dp[i][j] 表示 s1的前i个字符 和 s2的前j个字符，能否构成s3的前i+j个字符
     * {
     *     s1[i] == s3[i+j]                          dp[i][j] = dp[i-1][j]
     *     s2[j] == s3[i+j]                          dp[i][j] = dp[i][j-1]
     *     s1[i] != s3[i+j]  && s2[j] != s3[i+j]     dp[i][j] = false
     * }
     *
     */
    public boolean isInterleaveSecond(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        if (n + m != s3.length()) {
            return false;
        }
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] && s1.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a = s1.charAt(i - 1);
                char b = s2.charAt(j - 1);
                char c = s3.charAt(i + j - 1);
                if (a == c || b == c) {
                    dp[i][j] = a == c && dp[i - 1][j] || b == c && dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

































    /**
     * dp[i][j] 表示 s1的前i个字符 和 s2的前j个字符，是否能构成 s3的前i+j个字符
     * {
     *   s1[i] == s3[i + j] || s2[j] == s3[i + j]       dp[i][j] =  s1[i]== s3[i + j]&&dp[i-1][j] || s2[j] == s3[i + j]&&dp[i][j-1]
     *   s1[i] != s3[i + j] && s2[j] != s3[i + j]       dp[i][j] = false
     * }
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if (n + m != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0]  && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a = s1.charAt(i - 1);
                    char b = s2.charAt(j - 1);
                    char c = s3.charAt(i + j - 1);
                    if (a != c && b != c) {
                        dp[i][j] = false;
                        continue;
                    }
                    dp[i][j] = a == c && dp[i - 1][j] || b == c && dp[i][j - 1];
                }
            }

            return dp[n][m];
    }
}
