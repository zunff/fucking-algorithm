package 动态规划.D状态DP;


/**
 * 337. 打家劫舍 III 中等
 *
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 *
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 *
 * 提示：
 * 树的节点数在 [1, 104] 范围内
 * 0 <= Node.val <= 104
 */
public class HouseRobberIII337 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(new HouseRobberIII337().robSecond(root));
    }

    /**
     * dfs
     *
     * 状态：
     * 1. cur[0] 表示当前节点抢的时候的最高金额
     * 2. cur[1] 表示当前节点不抢的时候的最高节点
     *
     * {
     *     cur[0] = left[1] + right[1] + val
     *     cur[1] = max(left[0], left[1]) + max(right[0], right[1])
     * }
     */
    public int robSecond(TreeNode root) {
        int[] res = dfsSecond(root);
        return Math.max(res[0], res[1]);
    }

    public int[] dfsSecond(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] cur = new int[2];
        int[] left = dfsSecond(root.left);
        int[] right = dfsSecond(root.right);

        cur[0] = left[1] + right[1] + root.val;
        cur[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return cur;
    }































































    /**
     * 后序遍历，两个状态：
     * 1. 抢当前节点 arr[0] as rob
     * 2. 不抢当前节点 arr[1] as notRob
     * 初始化：root == null -> return [0,0]
     * {
     *     cur[0] = left[1] + right[1] + val
     *     cur[1] = max(left[0], left[1]) + max(right[0], right[1])
     * }
     * ans = max(arr[0], arr[1])
     *
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] res = new int[2];
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        res[0] = left[1] + right[1] + root.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}



