package 链表;

/**
 * 61. 旋转链表 中等
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * 
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class RotateRight61 {
    public static void main(String[] args) {
        
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            n++;
            tail = tail.next;
        }

        // 找断点，记得取余
        ListNode p = head;
        for (int i = 0; i < n - (k % n) - 1; i++) {
            p = p.next;
        }
        tail.next = head;
        head = p.next;
        p.next = null;
        return head;
    }
}
