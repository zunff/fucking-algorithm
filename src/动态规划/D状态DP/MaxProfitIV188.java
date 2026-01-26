package 动态规划.D状态DP;

/**
 * 188. 买卖股票的最佳时机 IV  困难
 *
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfitIV188 {

    public static void main(String[] args) {
        int[] prices = {2,1,4,5,2,9,7};
        System.out.println(new MaxProfitIV188().maxProfit(2, prices));
    }

    public int maxProfitSecond(int k, int[] prices) {
        return 0;
    }



































































    /**
     * 0..base-1
     *
     * hold[i][j] 表示在第i天，第j次持有的时候 的最大利润
     * sold[i][j] 表示在第i天，第j次不持有的时候 的最大利润
     *
     * hold[0][0] = -price[0]
     * hold[i][0] = max(hold[i - 1][0], -price[i])     表示第 i+1 天，第一次持有时 的最大利润
     * hold[0][j] = -price[i]                          表示第1天，第j+1次持有时 的最大利润
     * sold[i][0] = max(sold[i - 1][0], hold[i - 1][0] + price[i])
     * sold[0][j] = 0
     * {
     *     hold[i][j] = max(hold[i - 1][j], sold[i - 1][j - 1] - price[i])
     *     sold[i][j] = max(sold[i - 1][j], hold[i - 1][j] + price[i])
     * }
     *
     * ans = sold[n - 1][k - 1]
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] hold = new int[n][k];
        int[][] sold = new int[n][k];

        hold[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            hold[i][0] = Math.max(hold[i - 1][0], -prices[i]);
            sold[i][0] = Math.max(sold[i - 1][0], hold[i - 1][0] + prices[i]);
        }

        for (int j = 1; j < k; j++) {
            hold[0][j] = -prices[0];
        }


        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                hold[i][j] = Math.max(hold[i - 1][j], sold[i - 1][j - 1] - prices[i]);
                sold[i][j] = Math.max(sold[i - 1][j], hold[i - 1][j] + prices[i]);
            }
        }

        return sold[n - 1][k - 1];
    }
}
