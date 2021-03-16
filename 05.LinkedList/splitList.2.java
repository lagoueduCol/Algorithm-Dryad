class Solution {
    // 如果不使用dummy head
    private ListNode findMiddle(ListNode head) {
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

    public ListNode[] split(ListNode head) {
        ListNode mid = findMiddle(head);

        // 注意，此时mid是可能为空的!
        // 所以需要判断是否为空
        if (mid == null) {
            return new ListNode[] { null, null };
        }

        ListNode front = head;
        ListNode back = mid.next;
        mid.next = null;

        return new ListNode[] { front, back };
    }
}
// @lc code=end
