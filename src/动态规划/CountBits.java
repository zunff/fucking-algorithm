package 动态规划;

import java.util.Arrays;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * 6 110
 * 7 111
 * 8 1000
 * 9 1001
 * 10 1010
 * 11 1011
 * 12 1100
 * 13 1101
 * 14 1110
 * 15 1111
 * 16 10000
 *
 *
 * 提示：
 *
 * 0 <= n <= 105
 *
 */

public class CountBits {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(16)));
    }

    public static  int[] countBits(int n) {
        int[] dp = new int[n + 1];

        int height = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                dp[i] = 1;
                height = i;
                continue;
            }
            dp[i] = dp[i - height] + 1;
        }
        return dp;
    }

}
