package BFS和DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表 中等
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class CourseSchedule207 {
    public static void main(String[] args) {
        System.out.println(new CourseSchedule207().canFinish_Kahn(2, new int[][]{{1,0}}));
    }


    /**
     * Kahn 算法（入度法拓扑排序）：Kahn’s algorithm
     */
    public boolean canFinish_Kahn(int numCourses, int[][] prerequisites) {
        // 邻接表
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            int a = pre[0], b = pre[1];
            adj[b].add(a);
        }
        // 入度
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
        }

        // 找出入度为 0 的塞入队列作为开始
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            taken++;
            for (Integer p : adj[i]) {
                if (--inDegree[p] == 0) {
                    queue.offer(p);
                }
            }
        }

        return taken == numCourses;
    }









    /**
     *  DFS 判环（三色标记）
     *
     * 邻接矩阵：n*n 的表格，matrix[u][v]=1 表示有边 u->v（占空间大）
     * 邻接表：对每个点 u，存一个列表 adj[u]，里面是所有能从 u 直接走到的点 v
     * 也就是：adj[u] = { v | u -> v }
     */
    public boolean canFinish_Dfs(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        List<Integer>[] adj = buildAdj(numCourses, prerequisites);
        int[] status = new int[numCourses]; // 0 未判断，1 判断中，2 判断结束

        for (int i = 0; i < numCourses; i++) {
            if (status[i] == 0 && hasCycleDfs(i, status, adj)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCycleDfs(int cur, int[] status, List<Integer>[] adj) {
        // 先占用
        status[cur] = 1;
        for (Integer p : adj[cur]) {
            if (status[p] == 2) {
                continue;
            }
            if (status[p] == 1) {
                return true;
            }
            if (hasCycleDfs(p, status, adj)) {
                return true;
            }
        }
        // 已判断无环
        status[cur] = 2;
        return false;
    }



    public List<Integer>[] buildAdj(int n, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0], b = prerequisite[1];
            adj[a].add(b); // a -> b
        }
        return adj;
    }


}
