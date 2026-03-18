package BFS和DFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. 腐烂的橘子 中等
 *
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 * 示例 1：
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 *
 * 示例 2：
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 *
 * 示例 3：
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 */
public class OrangesRotting994 {

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
//        int[][] grid = {{0,2}};
//        int[][] grid = {{2,1,1}, {0,1,1}, {1,0,1}};
//        int[][] grid = {{0}};
        System.out.println(new OrangesRotting994().orangesRottingSecond(grid));
    }

    public int orangesRottingThird(int[][] grid) {
        return 0;
    }






















































    public int orangesRottingSecond(int[][] grid) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;
        int m = grid[0].length;

        int fresh = 0;

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int ans = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean success = false;
            for (int k = 0; k < size; k++) {
                int[] index = queue.poll();
                // 遍历四个方向
                for (int[] dir : dirs) {
                    int i = index[0] + dir[0];
                    int j = index[1] + dir[1];
                    if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 1) {
                        grid[i][j] = 2;
                        fresh--;
                        queue.offer(new int[]{i, j});
                        success = true;
                    }
                }
            }
            if (success) {
                ans++;
            }
        }

        return fresh == 0 ? ans : -1;
    }























































    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs  = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isSuccess = false;
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 1) {
                        isSuccess = true;
                        grid[i][j]  = 2;
                        queue.add(new int[]{i, j});
                    }
                }
            }
            if (isSuccess) {
                result++;
            }
        }

        // 遍历 grid 判断是否还有 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return result;
    }
}
