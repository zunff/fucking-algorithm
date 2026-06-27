package 二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. 二叉树的中序遍历 简单
 *
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class InorderTraversal21 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        List<Integer> ans = new InorderTraversal21().inorderTraversal(root);
        System.out.println(ans);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 先一路向左
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 栈顶就是最左边的叶子节点
            TreeNode pop = stack.pop();
            ans.add(pop.val);
            // 拿到右子树去下一个循环遍历，有点像递归
            if (pop.right != null) {
                cur = pop.right;
            }
        }
        return ans;
    }


    List<Integer> ans = new ArrayList<>();
    public List<Integer> inorderTraversal_backTracking(TreeNode root) {
        if (root == null) {
            return ans;
        }
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        ans.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return ans;
    }
}



