package 动态规划.C网格DP;

/**
 * 174. 地下城游戏 困难
 *
 * 恶魔们抓住了公主并将她关在了地下城 dungeon 的 右下角 。地下城是由 m x n 个房间组成的二维网格。
 * 我们英勇的骑士最初被安置在 左上角 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快解救公主，骑士决定每次只 向右 或 向下 移动一步。
 * 返回确保骑士能够拯救到公主所需的最低初始健康点数。
 * 注意：任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 *
 *
 * 示例 1：
 * 输入：dungeon = [[-2,-3,3],
 *                 [-5,-10,1],
 *                 [10,30,-5]]
 * 输出：7
 * 解释：如果骑士遵循最佳路径：右 -> 右 -> 下 -> 下 ，则骑士的初始健康点数至少为 7 。
 *
 * 示例 2：
 * 输入：dungeon = [[0]]
 * 输出：1
 *
 *
 * 提示：
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 */
public class CalculateMinimumHP174 {

    public static void main(String[] args) {
//        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
//        int[][] dungeon = {{100}};
        int[][] dungeon = {{2}, {1}};
        System.out.println(new CalculateMinimumHP174().calculateMinimumHPSecond(dungeon));
    }

    public int calculateMinimumHPThird(int[][] dungeon) {
        return 0;
    }























































    /**
     * 0...base-1
     *
     * dp[i][j] 表示在进入这个格子之前，至少需要多少血量才不会死，如果需要的生命值小于 1，则赋值为 1，代表至少需要一滴血
     * 初始化：dp[n - 1][m - 1] = max(1 - dungeon[n - 1][m - 1], 1)
     *        dp[i][m - 1] = max(dp[i + 1][m - 1] - dungeon[i][m - 1], 1)
     *        dp[n - 1][j] = max(dp[n - 1][j + 1] - dungeon[n - 1][j], 1)
     * {
     *     dp[i][j] = max(min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1)
     * }
     */
    public int calculateMinimumHPSecond(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[] dp = new int[m];
        dp[m - 1] = Math.max(1 - dungeon[n - 1][m - 1], 1);
        for (int j = m - 2; j >= 0; j--) {
            dp[j] = Math.max(dp[j + 1] - dungeon[n - 1][j], 1);
        }

        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1] = Math.max(dp[m - 1] - dungeon[i][m - 1], 1);
            for (int j = m - 2; j >= 0; j--) {
                dp[j] = Math.max(Math.min(dp[j], dp[j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0];


//        int n = dungeon.length;
//        int m = dungeon[0].length;
//        int[][] dp = new int[n][m];
//        dp[n - 1][m - 1] = Math.max(1 - dungeon[n - 1][m - 1], 1);
//        for (int i = n - 2; i >= 0; i--) {
//            dp[i][m - 1] = Math.max(dp[i + 1][m - 1] - dungeon[i][m - 1], 1);
//        }
//        for (int j = m - 2; j >= 0; j--) {
//            dp[n - 1][j] = Math.max(dp[n - 1][j + 1] - dungeon[n - 1][j], 1);
//        }
//
//        for (int i = n - 2; i >= 0; i--) {
//            for (int j = m - 2; j >= 0; j--) {
//                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
//            }
//        }
//        return dp[0][0];
    }

























































    /**
     * 0...base-1
     *
     * dp[i][j] 表示从 dungeon[i][j] 出发，但还没进入到这个点，到右下角的最少健康点数
     * 初始化：dp[n - 1][m - 1] = max(1 -dungeon[n - 1][m - 1], 1)
     *        dp[i][m - 1] = max(dp[i + 1][m - 1] - dungeon[i][m - 1], 1)
     *        dp[n - 1][j] = max(dp[n - 1][j + 1] - dungeon[n - 1][j], 1)
     * {
     *     dp[i][j] = max(min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1)
     * }
     *
     * ps：取max的意义：如果这个格子需要的最小生命 < 1，说明不需要初始生命就能进入，他能加血，因此，如果 < 1，那么只要活着走进这个格子即可
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        dp[n - 1][m - 1] = Math.max(1 - dungeon[n - 1][m - 1], 1);
        for (int i = n - 2; i >= 0; i --) {
            dp[i][m - 1] = Math.max(dp[i + 1][m - 1] - dungeon[i][m - 1], 1);
        }
        for (int j = m - 2; j >= 0; j--) {
            dp[n - 1][j] = Math.max(dp[n - 1][j + 1] - dungeon[n - 1][j], 1);
        }

        for (int i = n - 2; i >= 0; i --) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
