package 动态规划;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题 困难
 *
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 *
 * 示例 1：
 *
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 *
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= envelopes.length <= 105
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 105
 */
public class MaxEnvelopes {

    public static void main(String[] args) {

//        int[][] envelopes = {{4,5},{4,6},{6,7},{2,3},{1,1}};
        int[][] envelopes = {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        int result = 0;
        for (int i = 0; i < envelopes.length; i++) {
            result = Math.max(traverse(envelopes, i, 1, new boolean[envelopes.length]), result);
        }
        System.out.println(result);

        System.out.println(maxEnvelopes(envelopes));
    }


    public static int maxEnvelopes(int[][] envelopes) {
        int m = envelopes.length;
        if (m == 0) {
            return 0;
        }
        // 对 w 排序后，转化为 对h的最长递增子序列 问题
        Arrays.sort(envelopes, (o1, o2) -> o1[0] - o2[0]);

        int[] dp = new int[m];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                } else if (dp[i] == 0) {
                    dp[i] = 1;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }



    public static int traverse(int[][] envelopes, int cur, int deep, boolean[] isUse) {
        isUse[cur] = true;
        int curWeight = envelopes[cur][0];
        int curHeight = envelopes[cur][1];
        int result = deep;
        for (int i = 0; i < envelopes.length; i++) {
            if (!isUse[i] && curWeight < envelopes[i][0] && curHeight < envelopes[i][1]) {
                result = Math.max(traverse(envelopes, i, deep + 1, isUse), result);
                isUse[i] = false;
            }
        }
        return result;
    }
}
