package 双指针;

import java.util.Arrays;

/**
 * 283. 移动零 简单
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class MoveZeros283 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,2,9,0,1,0};
        new MoveZeros283().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int zeros = 0;
        int noZeros = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 0) {
                zeros++;
            } else {
                nums[noZeros] = nums[i];
                noZeros++;
            }
        }
        for (int i = 0; i < zeros; i++) {
            nums[n - i - 1] = 0;
        }
    }
}
