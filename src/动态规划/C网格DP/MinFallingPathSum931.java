package 动态规划.C网格DP;

/**
 * 931. 下降路径最小和 中等
 *
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 * 示例 1：
 * 输入：matrix = [[2,1,3],
 *                [6,5,4],
 *                [7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 *
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 *
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class MinFallingPathSum931 {

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
//        int[][] matrix = {{17,82},{1,-44}};
        System.out.println(new MinFallingPathSum931().minFallingPathSumSecond(matrix));
    }

    public int minFallingPathSumThird(int[][] matrix) {
        return 0;
    }































































    /**
     * dp[i][j] 表示 从第一行开始，到 matrix[i][j] 的最小下降路径
     * 初始化：dp[0][j] = matrix[0][j]
     * {
     *     dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j]
     * }
     */
    public int minFallingPathSumSecond(int[][] matrix) {
        int INF = 100 * 100 + 1;
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int left = j - 1 >= 0 ? dp[i - 1][j - 1] : INF;
                int right = j + 1 < m ? dp[i - 1][j + 1] : INF;
                dp[i][j] = Math.min(left, Math.min(dp[i - 1][j], right)) + matrix[i][j];
            }
        }

        int res = INF;
        for (int j = 0; j < m; j++) {
            res = Math.min(dp[n - 1][j], res);
        }
        return res;
    }
































































    /**
     * 0...base-1
     *
     * dp[i][j]表示从第一行到 matrix[i][j] 所需要的最小下降路径和
     * 初始化：dp[0][j] = matrix[0][j]
     * {
     *     dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1])
     * }
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int INF = 100 * 100 + 1;
        int res = INF;
        int[] dp = new int[m];

        for (int j = 0; j < n; j++) {
            dp[j] = matrix[0][j];
            if (n == 1) {
                res = Math.min(res, dp[j]);
            }
        }

        for (int i = 1; i < n; i++) {
            // 因为有 j - 1 和 j + 1，所以正序倒序遍历内层都没办法，只能保存下来 pre
            int left = INF;
            for (int j = 0; j < m; j++) {
                int right = j + 1 < m ? dp[j + 1] : INF;
                int tmp = Math.min(left, Math.min(dp[j], right)) + matrix[i][j];
                left = dp[j];
                dp[j] = tmp;
                if (i == n - 1) {
                    res = Math.min(dp[j], res);
                }
            }
        }
        return res;



//        int n = matrix.length;
//        int m = matrix[0].length;
//        int INF = 100 * 100 + 1;
//        int res = INF;
//        int[][] dp = new int[n][m];
//
//        for (int j = 0; j < n; j++) {
//            dp[0][j] = matrix[0][j];
//            if (n == 1) {
//                res = Math.min(res, dp[0][j]);
//            }
//        }
//
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                dp[i][j] = Math.min(
//                 j - 1 >= 0 ?  dp[i - 1][j - 1] : INF,
//                        Math.min(
//                                dp[i - 1][j],
//                                j + 1 < m ? dp[i - 1][j + 1] : INF
//                        )
//                ) + matrix[i][j];
//
//                if (i == n - 1) {
//                    res = Math.min(dp[i][j], res);
//                }
//            }
//        }
//        return res;
    }
}








