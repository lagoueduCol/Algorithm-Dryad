import MyLinkedList.ListNode;

/*
 * @lc app=leetcode.cn id=203 lang=java
 *
 * [203] 移除链表元素
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements/description/
 *
 * algorithms
 * Easy (46.96%)
 * Likes:    539
 * Dislikes: 0
 * Total Accepted:    134.8K
 * Total Submissions: 286.5K
 * Testcase Example:  '[1,2,6,3,4,5,6]\n6'
 *
 * 删除链表中等于给定值 val 的所有节点。
 * 
 * 示例:
 * 
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode p = head;

        while (p != null) {
            ListNode back = p.next;

            if (p.val != val) {
                tail.next = p;
                tail = p;
            }

            p = back;
        }

        tail.next = null;
        return dummy.next;
    }
}
// @lc code=end
