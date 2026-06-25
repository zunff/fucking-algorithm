package 链表;

/**
 * 206. 反转链表 简单
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class ReverseList206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
        head = new ReverseList206().reverseList_backTracking(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode reverseList_backTracking(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转后面的链表，返回新的头（最后一个节点）
        ListNode newHead = reverseList_backTracking(head.next);
        // 反转当前节点和下一个节点之间的关系
        head.next.next = head;
        // 断开原先的指向
        head.next = null;
        return newHead;
    }



    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

//    public ListNode reverseList(ListNode head) {
//        // A -> B -> C
//        ListNode pre = head;
//        ListNode cur = head.next;
//        while (cur != null) {
//            ListNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        // 记得把头节点next去掉，不然会 A B循环
//        head.next = null;
//        return pre;
//    }



}
