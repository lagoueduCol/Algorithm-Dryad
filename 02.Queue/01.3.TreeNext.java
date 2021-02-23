/*
// 测试平台链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        Node Q = null;
        if (root != null) {
            Q = root;
        }

        while (Q != null) {
            // 下一层前驱结点
            Node nextLevelPreNode = null;
            // 下一层的头结点
            Node nextLevelHead = null;
            // 顺序遍历当前层的每个结点
            Node curLevelNode = Q;
            while (curLevelNode != null) {
                // 如果得到一个下一层的结点
                if (curLevelNode.left != null) {
                    // 让下一层的前驱结点指向得到的下一层结点
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = curLevelNode.left;
                    }
                    nextLevelPreNode = curLevelNode.left;
                    // 设置下一层的头
                    if (nextLevelHead == null) {
                        nextLevelHead = curLevelNode.left;
                    }
                }
                // 如果得到一个下一层的结点
                if (curLevelNode.right != null) {
                    // 让下一层的前驱结点指向得到的下一层结点
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = curLevelNode.right;
                    }
                    nextLevelPreNode = curLevelNode.right;
                    // 设置下一层的头
                    if (nextLevelHead == null) {
                        nextLevelHead = curLevelNode.right;
                    }
                }
                curLevelNode = curLevelNode.next;
            }
            Q = nextLevelHead;
        }
        return root;
    }
}
