/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (64.03%)
 * Likes:    932
 * Dislikes: 0
 * Total Accepted:    139.4K
 * Total Submissions: 216.8K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 进阶：
 * 
 * 
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 列表中节点的数量在范围 sz 内
 * 1 
 * 0 
 * 1 
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

    private ListNode tmp = new ListNode();
    private ListNode tmpTail = tmp;
    private int len = 0;

    private ListNode ans = new ListNode();
    private ListNode ansTail = ans;

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

    private void append(ListNode p, int k) {
        // 将进来的结点添加到tmp链表中
        // 如果tmp链表中的结点个数 == k
        // 那么就需要把tmp 链表进行反转，然后再加入到ans链表中
        tmpTail.next = p;
        tmpTail = tmpTail.next;
        len++;

        if (len == k) {
            // 如果长为k，那么就需要进行一下翻转。
            // 这里记录下翻转之后的
            ListNode tail = tmp.next;
            ListNode head = reverse(tmp.next);

            // 反转后的链表是[head, tail]
            // 那么需要将这个链表添加到[ans, ansTail]里面
            ansTail.next = head;
            ansTail = tail;

            // 然后再把tmp链表清空
            len = 0;
            tmp.next = null;
            tmpTail = tmp;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        while (p != null) {
            ListNode back = p.next;

            p.next = null;
            append(p, k);

            p = back;
        }

        if (len > 0) {
            ansTail.next = tmp.next;
            ansTail = tmpTail;
        }

        ansTail.next = null;

        return ans.next;
    }
}
// @lc code=end
