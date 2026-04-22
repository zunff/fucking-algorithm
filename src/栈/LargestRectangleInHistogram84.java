package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形 困难
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 *
 *
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class LargestRectangleInHistogram84 {
    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram84().largestRectangleArea(new int[]{2,1,2}));
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int right = i - 1;
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                Integer index = stack.pop();
                // 以pop之后的栈顶作为左边界
                // 边界不是“当前 i 的左边”，而是“被弹出那个柱子 index 的左边第一个更矮的位置”
                // 如果不存在，-1表示直接以0作为左边界，因为此时栈顶的左边一定大于栈顶，不然就会在栈顶下面了
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, heights[index] * (right - left));
            }
            stack.push(i);
        }

        int right = n - 1;
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, heights[index] * (right - left));
        }
        return ans;
    }
}
