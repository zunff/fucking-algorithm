package 动态规划.C网格DP;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和 中等
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 */
public class MinimumTotal120 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        System.out.println(new MinimumTotal120().minimumTotalSecond(triangle));
    }

    public int minimumTotalThird(List<List<Integer>> triangle) {
        return 0;
    }






















































    /**
     * dp[i][j] 表示 以 triangle[i][j]为起点的最小路径和
     * 初始化：初始化最下面那行 dp[i][j] = triangle[i][j]
     * {
     *     dp[j] = min(dp[j + 1], dp[j]) + triangle[i][j]
     * }
     */
    public int minimumTotalSecond(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n - 1).size();
        int[] dp = new int[m];

        for (int j = 0; j < m; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j + 1 < triangle.get(i + 1).size()) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                } else {
                    dp[j] = dp[j] + triangle.get(i).get(j);
                }
            }
        }
        return dp[0];
    }
















































    /**
     * 0 ... base-1
     *
     * dp[j] 表示从顶部 到 triangle[i][j] 的最小路径和
     *
     * {
     *     j - 1 >= 0    dp[j] = min(dp[j], dp[j - 1]) + triangle[i][j]
     *     j - 1 < 0     dp[j] = dp[j] + triangle[i][j]
     * }
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.get(triangle.size() - 1).size()];
        int INF = 200 * 104 + 1;
        Arrays.fill(dp, INF);
        dp[0] = triangle.get(0).get(0);
        if (triangle.size() == 1) {
            return dp[0];
        }

        int res = INF;

        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> nums = triangle.get(i);
            for (int j = nums.size() - 1; j >= 0; j--) {
                if (j >= 1) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + nums.get(j);
                } else {
                    dp[j] += nums.get(j);
                }
                // 最后一行计算结果
                if (i == triangle.size() - 1) {
                    res = Math.min(res, dp[j]);
                }
            }
        }
        return res;
    }
}
