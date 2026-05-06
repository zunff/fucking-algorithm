package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列 中等
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permutations46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Permutations46().permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        backTracking(nums, new boolean[nums.length], new ArrayList<>());
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    public void backTracking(int[] nums, boolean[] selected, List<Integer> cur) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
        }

        for (int i = 0; i < nums.length; i++) {
            if (!selected[i]) {
                cur.add(nums[i]);
                selected[i] = true;
                backTracking(nums, selected, cur);
                cur.remove(cur.size() - 1);
                selected[i] = false;
            }
        }
    }
}
