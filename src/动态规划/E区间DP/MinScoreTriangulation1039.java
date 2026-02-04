package 动态规划.E区间DP;

/**
 * 1039. 多边形三角剖分的最低得分 中等
 *
 * 你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，其中 values[i] 是按 顺时针顺序 第 i 个顶点的值。
 * 假设将多边形 剖分 为 n - 2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 *
 * 示例 1：
 * 输入：values = [1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 *
 * 示例 2：
 * 输入：values = [3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 *
 * 示例 3：
 * 输入：values = [1,3,1,4,1,5]
 * 输出：13
 * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 *
 *
 * 提示：
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class MinScoreTriangulation1039 {

    public static void main(String[] args) {
        int[] values = {3,7,4,5};
        System.out.println(new MinScoreTriangulation1039().minScoreTriangulation(values));
    }

    public int minScoreTriangulationSecond(int[] values) {
        return 0;
    }






























































    /**
     * dp[i][j] 表示 values[i...j] 顺时针多边形的 三角形剖分的最低得分   闭区间
     * 假设最后一个被剖分出来的三角形是 values[i,k,j]，其中 i < k < j
     * 限制 j - i >= 2，不符合的dp[i][j]=0，符合的初始化为INF
     *
     * {
     *      dp[i][j] = k min(dp[i][k] + dp[k][j] + values[i]*value[k]*values[j])
     * }
     */
    public int minScoreTriangulation(int[] values) {
        int INF = 100 * 100 * 100 * 48;

        int n = values.length;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = INF;
            }
        }

        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i]*values[k]*values[j]);
                }
            }
        }


        return dp[0][n - 1];
    }

}
