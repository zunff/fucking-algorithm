package huawei;

import java.util.*;

public class HW161 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            // System.out.println(n);
            int[][] prerequires = new int[n][2];
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                // System.out.println(in.next());
                String[] split = in.next().split(",");
                prerequires[i][0] = Integer.parseInt(split[0]);
                int v = Integer.parseInt(split[1]);
                prerequires[i][1] = v;
                int version = Integer.parseInt(split[2]);
                if (map.containsKey(v)) {
                    map.put(v, Math.max(version, map.get(v)));
                } else {
                    map.put(v, version);
                }
            }

            if (hasCycleKahn(prerequires, n)) {
                System.out.println(false);
                continue;
            }
            for (int[] prerequire: prerequires) {
                int v = prerequire[1];
                System.out.println(prerequire[0] + "," + v + "," + map.get(v));
            }

            // System.out.println(n);
            // for (int i = 0; i < n; i++) {
            //     System.out.println(prerequires[i][0] + ", " + prerequires[i][1]);
            // }
            // System.out.println(map);

        }
    }

    private static boolean hasCycleKahn(int[][] prerequires, int n) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> ins = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int u = prerequires[i][0];
            int v = prerequires[i][1];
            // 记录邻接表
            List<Integer> list = adj.getOrDefault(u, new ArrayList<>());
            list.add(v);
            adj.put(u, list);
            // 记录入度
            ins.put(v, ins.getOrDefault(v, 0) + 1);
            // u 不存在的话记个入度0
            if (!ins.containsKey(u)) {
                ins.put(u, 0);
            }
        }
        // System.out.println(adj);
        // System.out.println(ins);

        Deque<Integer> queue = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : ins.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int taken = 0;
        while (!queue.isEmpty()) {
            int i = queue.pop();
            // 邻接表都-1
            List<Integer> list = adj.getOrDefault(i, new ArrayList<>());
            for (int j : list) {
                ins.put(j, ins.get(j) - 1);
                if (ins.get(j) == 0) {
                    queue.push(j);
                }
            }
            taken++;
        }

        return taken != ins.size();
    }
}
