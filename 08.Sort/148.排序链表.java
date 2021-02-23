import MyLinkedList.ListNode;

/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (67.66%)
 * Likes:    960
 * Dislikes: 0
 * Total Accepted:    135.1K
 * Total Submissions: 199.6K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 
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
  // 这里涉及到考点，找到一个链表的中间点
  private ListNode findMiddle(ListNode head) {
    ListNode s1 = head;
    ListNode s2 = head;
    ListNode pre = s1;
    while (s2 != null && s2.next != null) {
      pre = s1;
      s1 = s1.next;
      s2 = s2.next.next;
    }
    return s2 != null ? s1 : pre;
  }

  private ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // 与二叉树相比，这里需要把一个链表拆分成两部分
    // 二叉树直接可以通过root.left/root.right得到
    // 链表没有那么方便。
    ListNode mid = findMiddle(head);
    ListNode back = mid.next;
    mid.next = null;

    // 后序遍历左右两个链表
    ListNode i = mergeSort(head);
    ListNode j = mergeSort(back);

    // 遍历完成之后，开始将两个有序链表进行合并
    // 这里涉及到考点, dummy head.
    ListNode dummy = new ListNode();
    ListNode tail = dummy;

    // 这里是合并两个有序链表的模板
    while (i != null || j != null) {
      if (j == null || i != null && i.val <= j.val) {
        tail.next = i;
        tail = i;
        i = i.next;
      } else {
        tail.next = j;
        tail = j;
        j = j.next;
      }
    }
    tail.next = null;

    // 返回信息合并之后的结果
    return dummy.next;
  }

  public ListNode sortList(ListNode head) { return mergeSort(head); }
}
// @lc code=end
