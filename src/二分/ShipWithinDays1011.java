package 二分;

/**
 * 1011. 在 D 天内送达包裹的能力 中等
 *
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 示例 1：
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 *
 * 示例 2：
 * 输入：weights = [3,2,2,4,1,4], days = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 *
 * 示例 3：
 * 输入：weights = [1,2,3,1,1], days = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *
 * 提示：
 * 1 <= days <= weights.length <= 5 * 104
 * 1 <= weights[i] <= 500
 */
public class ShipWithinDays1011 {
    public static void main(String[] args) {
        System.out.println(new ShipWithinDays1011().shipWithinDays(new int[]{3,2,2,4,1,4}, 3));
    }

    public int shipWithinDays(int[] weights, int days) {
        return 0;
    }

















































    /**
     * 同理 410，本质上就是最大值最小化的问题，days天内把货运完，船一天内的最大运货量 M 的最大值越小越好
     */
    public int shipWithinDays_First(int[] weights, int days) {
        int l = 0;
        int r = 0;
        for (int w : weights) {
            l = Math.max(l, w);
            r += w;
        }

        int ans = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (can(mid, weights, days)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public boolean can_First(int M, int[] weight, int days) {
        int needDays = 1;
        int curWeight = 0;
        for (int w : weight) {
            if (curWeight + w > M) {
                needDays ++;
                curWeight = w;
                continue;
            }
            curWeight += w;
        }
        return needDays <= days;
    }

}
