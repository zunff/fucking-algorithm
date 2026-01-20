package 动态规划.D状态DP;

/**
 * 309. 买卖股票的最佳时机含冷冻期 中等
 * <p>
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfit309 {

    public static void main(String[] args) {
        int[] prices = {1, 4, 2};
        System.out.println(new MaxProfit309().maxProfit(prices));
    }

    public int maxProfitSecond(int[] prices) {
        return 0;
    }



































































    /**
     * 三种状态：
     * 1.hold[i] 表示在第i天持有股票的最大收益
     * 2.sold[i] 表示在第i天刚卖出股票时的最大收益（因此明天是冷却期）
     * 3.rest[i] 表示第i天没有持有股票，且明天是可买入状态的最大收益
     *
     * 其中 1表示持有的状态、2 3表示不持有的状态，最后的答案一定是不持有的状态
     * 初始化：hold[0] = -price[i]、sold[0] = 0、rest[0] = 0
     * {
     *     hold[i] = max(hold[i - 1], rest[i - 1] - price[i])    // 昨天就持有，或者是可买入状态今天买入
     *     sold[i] = hold[i - 1] + price[i]                     // 今天的价格 加上昨天持有状态下的收益
     *     rest[i] = max(sold[i - 1], rest[i - 1])              // 要么昨天刚卖，要么昨天也在休息
     * }
     *
     * ans=max(sold[n - 1], rest[n - 1])         最后一天的卖出收益 或 最后一天在休息的收益 取最大值
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] rest = new int[n];

        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i]);
            sold[i] = hold[i - 1] + prices[i];
            rest[i] = Math.max(sold[i - 1], rest[i - 1]);
        }
        return Math.max(sold[n - 1], rest[n - 1]);
    }
}
