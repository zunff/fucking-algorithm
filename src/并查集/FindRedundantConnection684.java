package 并查集;

import java.util.Arrays;

/**
 * 684. 冗余连接 中等
 *
 * 树可以看成是一个连通且 无环 的 无向 图。
 * 给定一个图，该图从一棵 n 个节点 (节点值 1～n) 的树中添加一条边后获得。添加的边的两个不同顶点编号在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
 * 图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
 *
 * 示例 1：
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 *
 * 示例 2：
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 *
 *
 * 提示:
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 */
public class FindRedundantConnection684 {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new FindRedundantConnection684().findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}})));
        System.out.println(Arrays.toString(new FindRedundantConnection684().findRedundantConnection_First(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        return null;
    }









    





















































    public int[] findRedundantConnection_First(int[][] edges) {
        int n = edges.length;
        DSU_First dsu = new DSU_First(n);
        for (int[] edge : edges) {
            if (!dsu.unionSuccess(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
        }
        return null;
    }

    static class DSU_First {
        private final int[] parent;
        private final int[] size;

        public DSU_First(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }


        public int find (int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean unionSuccess(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            // 形成环了
            if (ra == rb) {
                return false;
            }
            // 小树合并到大树下
            if (size[ra] < size[rb]) {
                parent[ra] = rb;
                size[rb] += size[ra];
            } else {
                parent[rb] = ra;
                size[ra] += size[rb];
            }
            return true;
        }
    }
}
