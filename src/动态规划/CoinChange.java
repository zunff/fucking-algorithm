package 动态规划;

import java.util.Arrays;

/**
 * 凑零钱问题
 * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {5, 6, 6, 2, 3};
        int amount = 12;
        System.out.println(traverse(coins, amount));

        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        System.out.println(traverse_memo(coins, amount, memo));

        System.out.println(iterate(coins, amount));
    }

    public static int iterate(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 子问题无解，那当前问题一定无解
                if (coin > i || dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static int traverse_memo(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            // 如果为 0 说明已经结束了
            return 0;
        }
        if (amount < 0) {
            // 子问题无解，返回-1
            return -1;
        }

        if (memo[amount] != -666) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 子问题：选当前硬币
            int sub = traverse(coins, amount - coin);
            if (sub == -1) {
                // 子问题无解，跳过
                continue;
            }
            // 两个子问题取最优结果：选 或 不选
            res = Math.min(sub + 1, res);
        }

        res = res == Integer.MAX_VALUE ? -1 : res;
        memo[amount] = res;
        return res;
    }


    /**
     * 状态 1：即递归的入参
     * 选择：递归的方法体
     * 状态 2：题目要的结果
     *
     * 状态 1的变化需要能通过选择，动态影响到状态 2，也就是不同的子问题
     *
     * 动态转移方程：状态 2 = 由于相同的选择，不同的状态 1的组合
     */
    public static int traverse(int[] coins, int amount) {
        if (amount == 0) {
            // 如果为 0 说明已经结束了
            return 0;
        }
        if (amount < 0) {
            // 子问题无解，返回-1
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 子问题：选当前硬币
            int sub = traverse(coins, amount - coin);
            if (sub == -1) {
                // 子问题无解，跳过
                continue;
            }
            // 两个子问题取最优结果：选 或 不选
            res = Math.min(sub + 1, res);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
