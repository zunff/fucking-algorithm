package 双指针;

/**
 * 11. 盛最多水的容器 中等
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class MaxArea11 {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(new MaxArea11().maxArea(height));
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1;
        int ans = 0;
        // 对撞指针
        while (i < j) {
            ans = Math.max(ans, (j - i) * Math.min(height[i], height[j]));
            // 移动更小的一边，想要面积更大只能寻找更高的边界
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }

//    public int maxArea(int[] height) {
//        int n = height.length;
//        int ans = 0;
//        for (int j = 1; j < n; j++) {
//            int i = 0;
//            while (i < j) {
//                int area = (j - i) * Math.min(height[i], height[j]);
//                ans = Math.max(ans, area);
//                i++;
//            }
//        }
//        return ans;
//    }
}













