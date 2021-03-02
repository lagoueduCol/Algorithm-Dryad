/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            ListNode tmp = head.next;

            head.next = dummy.next;
            dummy.next = head;

            head = tmp;
        }
        return dummy.next;
    }
}
// @lc code=end

