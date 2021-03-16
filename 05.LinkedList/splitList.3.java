class Solution {

    private ListNode findMiddle(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 如果都从dummy head出发
        // 那么当只有一个结点的时候
        // 需要注意split函数的处理
        ListNode pre = dummy;
        ListNode s1 = dummy;
        ListNode s2 = dummy;

        while (s2 != null && s2.next != null) {
            pre = s1;
            s1 = s1.next;
            s2 = s2.next.next;
        }

        return s2 != null ? s1 : pre;
    }

    private ListNode[] split(ListNode head) {
        ListNode mid = findMiddle(head);
        ListNode front = head;
        // 只有一个结点的进候，back容易指向head
        ListNode back = mid.next == head ? null : mid.next;
        mid.next = null;

        return new ListNode[] { front, back };
    }
}
// @lc code=end
