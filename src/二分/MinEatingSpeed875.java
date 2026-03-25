package 二分;

/**
 * 875. 爱吃香蕉的珂珂 中等
 *
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 * 示例 1：
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 *
 * 示例 2：
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 *
 * 示例 3：
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 *
 * 提示：
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class MinEatingSpeed875 {
    public static void main(String[] args) {
//        System.out.println(new MinEatingSpeed875().minEatingSpeed(new int[]{3,6,7,11}, 8));
//        System.out.println(new MinEatingSpeed875().minEatingSpeed(new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184}, 823855818));
//        System.out.println(new MinEatingSpeed875().minEatingSpeed(new int[]{159061844,517725047,376711793,557608450,386051217,915761849,443805917,541997964,420003629}, 771473915));
        System.out.println(new MinEatingSpeed875().minEatingSpeed(new int[]{312884470}, 968709470));
    }



    public int minEatingSpeed(int[] piles, int h) {
        return 0;
    }



























































    /**
     * k 是每个小时吃的香蕉数量的最大值，想让他尽量小，是最大值最小问题
     *
     * 上界：一小时吃完 sum(piles)
     * 下界：1  没说一次必须吃玩一堆        优化：sum(piles) / h
     *
     * 判断 k 根/小时 能否在 h小时内吃完香蕉：
     * 算出来 每一堆要吃几个小时，然后求和
     * 最后判断 count <= h 则能吃完
     *
     */
    public int minEatingSpeed_First(int[] piles, int h) {
        long sum = 0;
        for (int p : piles) {
            sum += p;
        }
        long l = sum / h == 0 ? 1 : sum / h;
        long r = sum;

        long ans = 0;

        while (l <= r) {
            long mid = (l + (r - l) / 2);
            if (can_First(mid, piles, h)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    public boolean can_First(long k, int[] piles, int h) {
        long hours = 0;
        for (int p : piles) {
            hours += (p + k - 1) / k;
        }
        return hours <= h;
    }
}
