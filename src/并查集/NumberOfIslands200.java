package 并查集;

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
//        char[][] grid = new char[][]{
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}
//        };
//        char[][] grid = new char[][]{
//                {'1', '1', '1'},
//                {'0', '1', '0'},
//                {'1', '1', '1'},
//        };
        char[][] grid = new char[][]{{'1'}, {'1'},};
        System.out.println(new NumberOfIslands200().numIslands(grid));

    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}};

        DSU dsu = new DSU(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '1') {
                    dsu.count--;
                    continue;
                }
                for (int[] dir : dirs) {
                    int i1 = i + dir[0];
                    int j1 = j + dir[1];
                    if (i1 >= 0 && j1 >= 0 && i1 < n && j1 < m && grid[i1][j1] == '1') {
                        dsu.union(i * m + j, i1 * m + j1);
                    }
                }
            }
        }

        return dsu.count;
    }

    static class DSU {
        private final int[] parent;
        private final int[] size;
        public int count;

        public DSU (int n, int m) {
            int len = n * m;
            parent = new int[len];
            size = new int[len];
            count = len;

            for (int i = 0; i < len; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }
            // 小树挂大树
            if (size[ra] < size[rb]) {
                parent[ra] = rb;
                size[rb] += size[ra];
            } else {
                parent[rb] = ra;
                size[ra] += size[rb];
            }
            count--;
        }
    }
}












