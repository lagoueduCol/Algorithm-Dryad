class ListNode {
    public int val = 0;
    public ListNode next = null;

    public ListNode() {

    }

    public ListNode(int x) {
        val = x;
    }
}

public class ans {

    /*
     * 思考题：如果在链表中进行查找的时候， 给定的并不是下标， 而是一个数 target， 或者是一个结点 ListNode target，
     * 应该如何正确地编写这个查找函数呢？
     *
     * 注意：给定的参数链表头head并不是一个假头
     */
    // 如果给定的不是下标，而是一个具体的数值。
    static private ListNode findNode(ListNode head, int target) {
        ListNode p = head;
        while (p != null) {
            if (p.val == target) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // 如果给定的是一个ListNode *target
    static private ListNode findNode(ListNode head, ListNode target) {
        ListNode p = head;
        while (p != null && target != null) {
            if (p == target) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // ------------ PART 2 -----------------------

    // 但是有时候，往往找到这个target之后，我们还需要执行一些操作。
    // 比如将这个target给删除掉的操作。那么，找到target的前面一个结点
    static private ListNode getPrevNode(ListNode dummy, int target) {
        ListNode pre = dummy;
        ListNode p = dummy.next;

        while (p != null) {
            if (p.val == target) {
                return pre;
            }
            pre = p;
            p = p.next;
        }

        return pre;
    }

    // 当给定的链表不是一个带dummy head的链表的时候，我们改造成一个带dummy
    // head的链表再操作
    static private ListNode deleteNode(ListNode head, int target) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = getPrevNode(dummy, target);

        // 删除结点
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        return dummy.next;
    }

    // ========= TEST CODE ==============

    // 以下是测试代码
    private static ListNode fromArrayToList(int[] A) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int x : A) {
            ListNode p = new ListNode(x);
            tail.next = p;
            tail = tail.next;
        }
        tail.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        ListNode head = fromArrayToList(A);
        for (int x : A) {
            ListNode ret = findNode(head, x);
            assert ret.val == x;

            assert ret == findNode(head, ret);
        }
    }
}