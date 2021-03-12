/*
 * 给定一个链表，将链表从尾部开始，k个一组进行反转。 如果最左边的那个片段不足k个，那么不去翻转。 
 * 
 * A = [1, 2, 3, 4, 5], k = 3
 * 
 * 输出：[1, 2, 5, 4, 3]
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
        // 假设输入为 head = [1, 2, 3, 4, 5]
        // 首先我们将链表进行翻转 head = [5,4,3,2,1]
        head = reverse(head);
        // 那么就可以按照原来顺序的k段分组的方式来进行了。

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

        // 此时ans =[3,4,5,2,1]
        // 最后再进行一次反转
        // 函数会返回[1,2,5,4,3];
        return reverse(ans.next);
    }
}

