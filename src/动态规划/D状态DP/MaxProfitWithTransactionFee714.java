package 动态规划.D状态DP;

/**
 * 714. 买卖股票的最佳时机含手续费 中等
 *
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要支付一次手续费。
 *
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 * 提示：
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 */
public class MaxProfitWithTransactionFee714 {

    public static void main(String[] args) {
        int[] prices = {1,3,7,5,10,3};
        System.out.println(new MaxProfitWithTransactionFee714().maxProfitSecond(prices, 3));
    }

    public int maxProfitThird(int[] prices, int fee) {
        return 0;
    }































































    /**
     * 状态：
     * hold[i] 表示第i天持有时的最大利润
     * sold[i] 表示第i天不持有时的最大利润
     * 初始化:hold[0] = -price[0]、sold[0] = 0
     * {
     *     hold[i] = max(hold[i - 1], sold[i - 1] - price[i])
     *     sold[i] = max(sold[i - 1], hold[i - 1] + price[i] - fee)
     * }
     */
    public int maxProfitSecond(int[] prices, int fee) {
        int n = prices.length;
        int hold = -prices[0];
        int sold = 0;

        int lastHold;
        for (int i = 1; i < n; i++) {
            lastHold = hold;
            hold = Math.max(hold, sold - prices[i]);
            sold = Math.max(sold, lastHold + prices[i] - fee);
        }
        return sold;
    }































































    /**
     * 0...base-1
     * 两种状态：
     * 1. hold[i] 表示第i天持有时的最大利润
     * 2. sold[i] 表示第i天不持有时的最大利润
     * 初始化：hold[0] = -price[0]、sold[i] = 0
     * {
     *     hold[i] = max(hold[i - 1], sold[i - 1] - price[i])           继续昨天的持有、或者昨天是不持有状态，今天买回来
     *     sold[i] = max(sold[i - 1], hold[i - 1] + price[i] - fee)     继续昨天的不持有状态，或者昨天是持有状态，今天卖掉
     * }
     * ans = sold[n - 1]        最后一天卖出的最大利润
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] hold = new int[n];
        int[] sold = new int[n];
        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
        }

        return sold[n - 1];
    }
}












