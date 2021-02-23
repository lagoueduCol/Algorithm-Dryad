/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (l1 != null || l2 != null) {
            if (l2 == null || l1 != null && l1.val <= l2.val) {
                ListNode back = l1.next;

                tail.next = l1;
                tail = l1;

                l1 = back;
            } else {
                ListNode back = l2.next;

                tail.next = l2;
                tail = l2;

                l2 = back;
            }
        }
        tail.next = null;

        return dummy.next;
    }
}
// @lc code=end

