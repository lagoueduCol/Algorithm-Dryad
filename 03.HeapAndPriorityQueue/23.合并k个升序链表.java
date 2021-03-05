import jdk.tools.jlink.resources.plugins;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个升序链表
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (54.15%)
 * Likes:    1176
 * Dislikes: 0
 * Total Accepted:    218.8K
 * Total Submissions: 402.3K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * ⁠ 1->4->5,
 * ⁠ 1->3->4,
 * ⁠ 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 
 * 
 * 示例 2：
 * 
 * 输入：lists = []
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 输入：lists = [[]]
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * 
 * 
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
    public ListNode mergeKLists(ListNode[] A) {
        final int N = A == null ? 0 : A.length;

        Queue<ListNode> Q = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (int i = 0; i < N; i++) {
            ListNode t = A[i];
            if (t != null) {
                Q.add(t);
            }
        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (!Q.isEmpty()) {
            ListNode cur = Q.poll();

            tail.next = cur;
            tail = cur;

            if (cur.next != null) {
                Q.add(cur.next);
            }
        }

        tail.next = null;
        return dummy.next;
    }
}
// @lc code=end

