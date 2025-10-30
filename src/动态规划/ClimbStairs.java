package 动态规划;

import java.util.Arrays;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 *
 * 1 <= n <= 45
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int n = 10;

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        System.out.println(traverse(n, 0, memo));

        System.out.println(iterator(n));
    }


    public static int iterator_opt_memo(int n) {
        int a = 1;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static int iterator(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int traverse(int n, int step, int[] memo) {
        if (step == n) {
            return 1;
        }
        if (step > n) {
            return 0;
        }
        if (memo[step] != -1) {
            return memo[step];
        }
        memo[step] = traverse(n, step + 1, memo) + traverse(n, step  + 2, memo);
        return memo[step];
    }
}
