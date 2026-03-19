package 并查集;

/**
 * 547. 省份数量 中等
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class FindCircleNum547 {
    public static void main(String[] args) {
        System.out.println(new FindCircleNum547().findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
    }
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);

        // 由于无向图的对称性，只需要遍历右上半角，因为 isConnected[i][j] == isConnected[j][i]
        for (int i = 0 ; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果两个城市联通，尝试把两块连通块连起来
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }
        return dsu.count;
    }

    static class DSU {
        private int[] parent; //parent[i] 是 元素 i 的上级
        public int count; // 连通块数量，初始为元素数量

        // 优化2，记下来每棵树的大小
        private int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // 自成一棵树，自己就是根
                size[i] = 1;
            }
        }

        public int find(int x) {
            // 向上寻找根节点
//            while (x != parent[x]) {
//                x = parent[x];
//            }
            // 优化1：路径压缩，直接把找到的根作为自己的parent
            if (x != parent[x]) {
                parent[x] = find(x);
            }
            return x;
        }

        public void union(int a, int b) {
            // 合并两个点所在的树
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                // 本就在同一棵树
                return;
            }
            // 合并两个连通块，把A连通块挂到联通块B下
//            parent[rootA] = rootB;
            // 优化2：小树挂大树
            if (size[rootA] < size[rootB]) {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            } else {
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            }

            // 减少连通块数量
            count--;

        }
    }
}
