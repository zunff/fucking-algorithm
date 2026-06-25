package 链表;

/**
 * 876. 链表的中间结点 简单
 * 
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 * 
 * 示例 2：
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 * 
 * 提示：
 * 链表的结点数范围是 [1, 100]
 * 1 <= Node.val <= 100
 */
public class MiddleNode61 {
    public static void main(String[] args) {
        
    }
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 偶数节点时，fast会停在null，slow会因此走多一格
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
