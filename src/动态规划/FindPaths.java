package 动态规划;

/**
 * 576. 出界的路径数
 *
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 *
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 10^9 + 7 取余 后的结果。
 *
 * 示例 1：
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 *
 *
 * 示例 2：
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 *
 * 提示：
 * * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 */
public class FindPaths {

    public static void main(String[] args) {
        System.out.println(new FindPaths().findPaths_dfs(1, 3, 3, 0, 1));
    }

    int MOD = 10000_0000_7;
    int[][] dirs = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

        return 0;
    }

    int[][][] cache;
    int max_x;
    int max_y;

    public int findPaths_dfs(int m, int n, int maxMove, int startRow, int startColumn) {
        max_x = m;
        max_y = n;
        cache = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
        return dfs(startRow, startColumn, maxMove);
    }

    public int dfs(int x, int y, int step) {
        if (x >= max_x || y >= max_y || x < 0 || y < 0) {
            return 1;
        }
        if (step == 0) {
            return 0;
        }
        if (cache[x][y][step] != -1) {
            return cache[x][y][step];
        }
        int ans = 0;
        // 遍历四个方向
        for (int[] dir : dirs) {
            ans += dfs(x + dir[0], y + dir[1], step - 1);
            ans %= MOD;
        }
        cache[x][y][step] = ans;
        return ans;
    }
}
