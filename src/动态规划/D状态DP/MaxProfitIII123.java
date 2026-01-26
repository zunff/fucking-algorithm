package 动态规划.D状态DP;

/**
 * 123. 买卖股票的最佳时机 III 困难
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class MaxProfitIII123 {

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        System.out.println(new MaxProfitIII123().maxProfitSecond(prices));
    }

    /**
     * hold1[i] 表示第i天持有时的最大收益
     * sold1[i] 表示第i天不持有时的最大收益
     * hold2[i] 表示第i天持有第二次时的最大收益
     * sold2[i] 表示第i天第二次不持有时的最大收益
     * {
     *     hold1[i] = max(hold1[i - 1], -price[i])
     *     sold1[i] = max(sold1[i - 1], hold1[i - 1] + price[i])
     *     hold2[i] = max(hold2[i - 1], sold1[i - 1] - price[i])
     *     sold2[i] = max(sold2[i - 1], hold2[i - 1] + price[i])
     * }
     */
    public int maxProfitSecond(int[] prices) {
        int n = prices.length;
        int hold1 = -prices[0];
        int hold2 = -prices[0];
        int sold1 = 0, sold2 = 0;

        int lastSold1;
        for (int i = 1; i < n; i++) {
            lastSold1 = sold1;
            sold1 = Math.max(sold1, hold1 + prices[i]);
            hold1 = Math.max(hold1, -prices[i]);
            sold2 = Math.max(sold2, hold2 + prices[i]);
            hold2 = Math.max(hold2, lastSold1 -prices[i]);
        }
        return sold2;
    }


























































    /**
     * 状态：
     * 1.没有进行一次买卖   利润恒为0
     * 2.买了第一次但没卖   hold1[i] 表示在第i天持有第一次买入时的最大利润     hold1[0] = -price[0]
     * 3.买了第一次且卖了   sold1[i] 表示在第i天不持有第一次买入时的最大利润   sold1[0] = 0
     * 4.买了的二次但没卖   hold2[i] 表示在第i天持有第二次买入时的最大利润     hold2[0] = -price[0]
     * 5.买了第二次且卖了   sold2[i] 表示在第i天不持有第二次买入时的最大利润   sold2[0] = 0
     *
     * {
     *     hold1[i] = max(hold1[i - 1], -price[i])                   昨天就持有 或第一次买入
     *     sold1[i] = max(sold1[i - 1], hold1[i - 1] + price[i])     昨天就不持有 或昨天持有今天卖出
     *     hold2[i] = max(hold2[i - 1], sold1[i - 1] - price[i])     昨天就持有 或昨天是第一次卖出状态今天买入
     *     sold2[i] = max(sold2[i - 1], hold2[i - 1] + price[i])     昨天就是卖出状态 或昨天是第二次持有状态今天卖出
     * }
     */
    public int maxProfit(int[] prices) {
        int hold1 = -prices[0];
        int hold2 = -prices[0];
        int sold1 = 0, sold2 = 0;

        int n = prices.length;
        int lastSold1;
        for (int i = 1; i < n; i++) {
            lastSold1 = sold1;

            sold1 = Math.max(sold1, hold1 + prices[i]);
            hold1 = Math.max(hold1, -prices[i]);

            sold2 = Math.max(sold2, hold2 + prices[i]);
            hold2 = Math.max(hold2, lastSold1 - prices[i]);
        }
        return sold2;
    }
}
