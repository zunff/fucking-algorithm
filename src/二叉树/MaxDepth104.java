package 二叉树;

/**
 * 104. 二叉树的最大深度 简单
 *
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 *
 * 提示：
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 */
public class MaxDepth104 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        System.out.println(new MaxDepth104().maxDepth(root));
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        if (root.left != null) {
            left = maxDepth(root.left);
        }
        int right = 0;
        if (root.right != null) {
            right = maxDepth(root.right);
        }
        return Math.max(left, right) + 1;
    }
}
