package 动态规划;

import java.util.Arrays;

/**
 * 实际上，这几种方式都是动态规划，只是效率的高低
 * 动态规划三点：
 * 1、状态转移方程：这里是 f(n) = f(n-1) + f(n-2)，可以发现，无论哪种方式，都是围绕着状态转移方程
 * 2、最优子结构：子问题间必须互相独立，互不影响
 * 3、重叠子问题：fib_traverse 中，同一个Fibonacci数会被计算多次，所以用了memo数组来避免，也是后面演变的遍历方案中的dp数组
 */
public class Fibonacci {
    public static void main(String[] args) {
        int n = 40;
        long start = System.currentTimeMillis();
        System.out.println(fib_traverse(n));
        long end = System.currentTimeMillis();
        System.out.println("fib_traverse  use time：" + (end - start));

        start = System.currentTimeMillis();
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println(fib_traverse_optimize(n, memo));
        end = System.currentTimeMillis();
        System.out.println("fib_traverse_optimize use time：" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(fib_iterate(n));
        end = System.currentTimeMillis();
        System.out.println("fib_iterate use time：" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(fib_iterate_opt_memo(n));
        end = System.currentTimeMillis();
        System.out.println("fib_iterate_opt_memo use time：" + (end - start));
    }

    /**
     * 迭代的方式、优化空间复杂度，因为只有 n - 1、n - 2 被用到了，所以只需要两个变量即可，不需要O(n)的数组
     */
    public static int fib_iterate_opt_memo(int n) {
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int now = a + b;
            // 滚动两个变量
            a = b;
            b = now;
        }
        return b;
    }


    /**
     * 迭代的方式、自带一个数组，自底向上计算，O(n)
     */
    public static int fib_iterate(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 递归、使用 memo 数组减枝，避免重复计算，自顶向下计算，O(n)
     * ps: 递归的时间复杂度为子问题计算数量，这里使用了memo、每个子问题只会计算一次，所以是O(n)
     */
    public static int fib_traverse_optimize(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = fib_traverse_optimize(n - 1, memo) + fib_traverse_optimize(n - 2, memo);
        return memo[n];
    }

    /**
     * 最简单的递归实现，时间复杂度 O(2^n)
     */
    public static int fib_traverse(int n) {
        if (n <= 1) {
            return n;
        }
        return fib_traverse(n - 1) + fib_traverse(n - 2);
    }
}