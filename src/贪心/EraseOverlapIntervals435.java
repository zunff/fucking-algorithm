package 贪心;

import java.util.Arrays;

/**
 * 435. 无重叠区间 中等
 *
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 *
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 * 提示:
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class EraseOverlapIntervals435 {
    public static void main(String[] args) {
//        int[][] intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
//        int[][] intervals = new int[][]{{1,2},{1,2},{1,2}};
        int[][] intervals = new int[][]{{1,2},{2,3}};
        System.out.println(new EraseOverlapIntervals435().eraseOverlapIntervals(intervals));
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 1; i < intervals.length; i++)  {
            int[] interval = intervals[i];
            int[] pre = intervals[i - 1];
            if (interval[0] < pre[1]) {
                ans++;
                // 不删除，合并前面的区间到当前区间到位置，并用min尽量使得右区间更小，尽量避免与下一个区间冲突
                intervals[i][1] = Math.min(interval[1], pre[1]);
            }
        }
        return ans;
    }
}
