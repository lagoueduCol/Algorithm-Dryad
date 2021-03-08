import MyLinkedList.ListNode;

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

  public ListNode[] split(ListNode head) {
    // 这里获取了链表的中间结点
    ListNode mid = findMiddleNode(head);
    // 拿到链表的中间结点之后，可以得到链表的后半部分的开头
    ListNode back = mid.next;
    // 把链表拆分为两半
    mid.next = null;
    // 返回两个链表的头部
    return new ListNode[] {head, back};
  }
}
// @lc code=end
