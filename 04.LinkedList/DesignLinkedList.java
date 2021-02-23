/*
 *
 * [707] 设计链表
 *
 * 测试平台链接：
 * - https://leetcode-cn.com/problems/design-linked-list/
 */

class MyLinkedList {
    // 实现单链表
    // 1. 假设链表中的所有节点都是 0-index的。

    class ListNode {
        public int val = 0;
        public ListNode next = null;
        public ListNode() {}
        public ListNode(int x) {
            val = x;
        }
    }

    private ListNode dummy = new ListNode();
    private ListNode tail = dummy;
    private int length = 0;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    private ListNode getPreNode(int index) {
        ListNode front = dummy.next;
        ListNode back = dummy;
        for (int i = 0; i < index; i++) {
            back = front;
            front = front.next;
        }
        return back;
    }

    // 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    public int get(int index) {
        if (index < 0 || index >= length) {
            return -1;
        }
        return getPreNode(index).next.val;
    }

    // 在链表的第一个元素之前添加一个值为 val 的节点。
    // 插入后，新节点将成为链表的第一个节点。
    public void addAtHead(int val) {
        ListNode p = new ListNode(val);
        p.next = dummy.next;
        dummy.next = p;
        // NOTE change tail
        if (tail == dummy) {
            tail = p;
        }
        length++;
    }

    // 将值为 val 的节点追加到链表的最后一个元素。
    public void addAtTail(int val) {
        tail.next = new ListNode(val);
        tail = tail.next;
        length++;
    }

    // 在链表中的第 index 个节点之前添加值为 val  的节点。
    // 1. 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
    // 2. 如果 index 大于链表长度，则不会插入节点。
    // 3. 如果index小于0，则在头部插入节点。
    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        } else if (index == length) {
            addAtTail(val);
            return;
        } else if (index <= 0) {
            addAtHead(val);
            return;
        }
        ListNode pre = getPreNode(index);
        ListNode p = new ListNode(val);
        p.next = pre.next;
        pre.next = p;
        // NOTE: here tail has been changed
        length++;
    }

    // 如果索引 index 有效，则删除链表中的第 index 个节点。
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        ListNode pre = getPreNode(index);

        // NOTE: delete -> change tail
        if (tail == pre.next) {
            tail = pre;
        }
        length--;

        pre.next = pre.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

