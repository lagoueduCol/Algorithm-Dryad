/*
 * @lc app=leetcode.cn id=143 lang=java
 *
 * [143] 重排链表
 *
 * https://leetcode-cn.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (59.50%)
 * Likes:    500
 * Dislikes: 0
 * Total Accepted:    77.9K
 * Total Submissions: 130.9K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 示例 1:
 * 
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 
 * 示例 2:
 * 
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    private ListNode split(ListNode head) {
        ListNode pre = head;
        ListNode s1 = head;
        ListNode s2 = head;

        while (s2 != null && s2.next != null) {
            pre = s1;
            s1 = s1.next;
            s2 = s2.next.next;
        }

        return s2 != null ? s1 : pre;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode p = head;

        while (p != null) {
            ListNode back = p.next;

            p.next = dummy.next;
            dummy.next = p;

            p = back;
        }

        return dummy.next;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = split(head);
        ListNode front = head;
        ListNode back = mid.next;
        mid.next = null;

        back = reverse(back);

        boolean isFront = true;
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (front != null || back != null) {
            if (back == null || isFront && front != null) {
                tail.next = front;
                tail = tail.next;
                front = front.next;
            } else {
                tail.next = back;
                tail = tail.next;
                back = back.next;
            }
            isFront = !isFront;
        }
        tail.next = null;

    }
}
// @lc code=end
