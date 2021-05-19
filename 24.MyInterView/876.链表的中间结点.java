/*
 * @lc app=leetcode.cn id=876 lang=java
 *
 * [876] 链表的中间结点
 *
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/description/
 *
 * algorithms
 * Easy (69.94%)
 * Likes:    312
 * Dislikes: 0
 * Total Accepted:    96.4K
 * Total Submissions: 137.9K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next
 * = NULL.
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 给定链表的结点数介于 1 和 100 之间。
 * 
 * 
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
    public ListNode middleNode(ListNode head) {
        // 注意，这里在返回中间结点的时候，如果是偶数个，需要返回后面的那个结点
        // 与我们文章第05讲中介绍的不一样。
        // 我们在拆分链表的时候，[1,2,3,4]中间的结点，我们返回2
        // 但是在这里[1,2,3,4] -> 返回3

        // s1表示一次走一步
        ListNode s1 = head;
        // s2表示一次走2步
        ListNode s2 = head;
        // 记录s1前面的那个结点
        ListNode pre = head;

        while (s2 != null && s2.next != null) {
            pre = s1;
            s1 = s1.next;
            s2 = s2.next.next;
        }

        // 如果只有一个点. s2 != null, 返回 => s1
        // 如果有2个点, s2 == null, s1指向最后一个结点 => s1
        // 如果有3个点, s2 != null, s2指向最后一个结点 => s1
        // 所以，总是返回s1
        return s1;
    }
}
// @lc code=end

