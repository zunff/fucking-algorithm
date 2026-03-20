package 并查集;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 128. 最长连续序列 中等
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 示例 3：
 * 输入：nums = [1,0,1,2]
 * 输出：3
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestConsecutiveSequence128 {

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence128().longestConsecutive_Hash(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        return 0;
    }






























































    public int longestConsecutive_Hash(int[] nums) {
        HashSet<Integer> number = new HashSet<>();
        for (int num : nums) {
            number.add(num);
        }

        int ans = 0;

        for (int num : number) {
            // 不存在num - 1说明是起点，只在起点开始遍历，避免 O(n^2)
            if (!number.contains(num - 1)) {
                int cur = num;
                int len = 1;
                while (number.contains(cur + 1)) {
                    cur = cur + 1;
                    len++;
                }
                ans = Math.max(len, ans);
            }
        }

        return ans;
    }































































    public int longestConsecutive_DSU_First(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 数值与下边映射
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i <n; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, i);
            }
        }
        DSU_First dsu = new DSU_First(n);
        int ans = 1;
        for (int num : nums) {
            Integer nextIndex = map.get(num + 1);
            if (nextIndex != null) {
                int curIndex = map.get(num);
                ans = Math.max(dsu.union(curIndex, nextIndex), ans);
            }
        }
        return ans;
    }


    static class DSU_First {
        private final int[] parent;
        private final int[] size;

        private DSU_First(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }


        public int union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return size[ra];
            }

            // 小树 挂在 大树 根节点下面
            if (size[ra] > size[rb]) {
                parent[rb] = ra;
                size[ra] += size[rb];
                return size[ra];
            } else {
                parent[ra] = rb;
                size[rb] += size[ra];
                return size[rb];
            }
        }
    }
}
