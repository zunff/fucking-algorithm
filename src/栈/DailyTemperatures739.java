package 栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度 中等
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures739 {
    public static void main(String[] args) {
        int[] ints = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(new DailyTemperatures739().dailyTemperatures(ints)));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        // Next Greater Element 单调栈里存下标  这里是递减  2 -> 1 -> top
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer top = stack.pop();
                ans[top] = i - top;
            }
            stack.push(i);
        }

        return ans;
    }
}
