package 动态规划;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
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
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 */
public class MinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        System.out.println(traverse(triangle, 0, 0));

        int[][] memo = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < triangle.size(); i++) {
            Arrays.fill(memo[i], -1);
        }
        System.out.println(traverse_memo(triangle, 0, 0, memo));

        System.out.println(minimumTotal(triangle));

        System.out.println(minimumTotal_single_arr(triangle));
    }

    public static int minimumTotal_single_arr(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                Integer cur = triangle.get(i).get(j);
                dp[j] = Math.min(dp[j], dp[j + 1]) + cur;
            }
        }
        return dp[0];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                Integer cur = triangle.get(i).get(j);
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + cur;
            }
        }
        return dp[0][0];
    }


    public static int traverse_memo(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        if (i >= triangle.size()) {
            return 0;
        }
        if (j >= triangle.get(i).size()) {
            return 0;
        }
        if (memo[i][j] >= 0) {
            return memo[i][j];
        }
        memo[i][j] = Math.min(traverse_memo(triangle, i + 1, j, memo), traverse_memo(triangle, i + 1, j + 1, memo)) + triangle.get(i).get(j);
        return memo[i][j];
    }

    public static int traverse(List<List<Integer>> triangle, int i, int j) {
        if (i >= triangle.size()) {
            return 0;
        }
        if (j >= triangle.get(i).size()) {
            return 0;
        }
        return Math.min(traverse(triangle, i + 1, j), traverse(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }
}
