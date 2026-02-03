package 动态规划.E区间DP;

/**
 * 486. 预测赢家 中等
 *
 * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），
 * 取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
 * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 示例 1：
 * 输入：nums = [1,5,2]
 * 输出：false
 * 解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 false 。
 *
 * 示例 2：
 * 输入：nums = [1,5,233,7]
 * 输出：true
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 107
 */
public class PredictTheWinner486 {
    public static void main(String[] args) {
        int[] nums = {1,5,2};
        System.out.println(new PredictTheWinner486().predictTheWinnerSecond(nums));
    }

    public boolean predictTheWinnerThird(int[] nums) {
        return false;
    }

































































    /**
     * dp[i][j] 表示 在区间nums[i...j]内，先手玩家与后手玩家的最大分差
     * 初始化：i == j dp[i][j] = nums[i]
     *
     * {
     *     dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
     * }
     */
    public boolean predictTheWinnerSecond(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0 ; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i+ 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }






























































    /**
     * 0...base-1
     *
     * 状态定义：
     * dp[i][j] 表示玩家1在 nums[i...j] 中与玩家2的最大分差，即 dp[i][j] = 先手玩家 - 后手玩家 的最大值
     * 初始化：i == j   dp[i][j] = nums[i]
     * {
     *  dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
     * }
     * ps：为什么是 - dp[i + 1][j]，因为我们想要的是nums[i] + (dp[i + 1][j] 中玩家1先手的情况下的差值)
     *     但是我们选之后接下来就是玩家2先手，即dp[i + 1][j] = player2 - player1，但我们想要的是玩家1先手，所以player1 - player2 = -dp[i + 1][j]
     */
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
