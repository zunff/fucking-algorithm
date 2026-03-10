package BFS和DFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 200. 岛屿数量 中等
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class NumberOfIslands200 {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
//        char[][] grid = new char[][]{
//                {'1', '1', '1'},
//                {'0', '1', '0'},
//                {'1', '1', '1'},
//        };
        System.out.println(new NumberOfIslands200().numIslandsBfs(grid));
    }

    public int numIslandsBfs(char[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    // bfs 把整个岛淹没
                    grid[i][j] = '0';
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] index = queue.poll();
                        for (int[] d : direction) {
                            int x = index[0] + d[0];
                            int y = index[1] + d[1];
                            if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                queue.add(new int[]{x, y});
                            }
                        }
                    }
                }
            }
        }
        return result;
    }







    public int numIslandsDfs(char[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = grid[i][j];
                if (c == '1') {
                    result++;
                    // 把整座岛沉掉
                    dfs(grid, n, m, i, j);
                }
            }
        }
        return result;
    }
    public void dfs(char[][] grid, int n, int m, int i, int j) {
        if (i == n || j == m  || j == -1 || i == -1 || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, n, m, i - 1, j);
        dfs(grid, n, m, i + 1, j);
        dfs(grid, n, m, i, j + 1);
        dfs(grid, n, m, i, j - 1);
    }
}
