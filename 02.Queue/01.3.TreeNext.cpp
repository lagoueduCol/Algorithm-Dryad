/*
// 测试平台链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        // 设置当前层
        Node *Q = nullptr;
        // 如果不为空，那么指向当前层的头结点
        if (root) {
            Q = root;
        }

        while (Q) {
            // 下一层结点的前驱
            Node *nextLevelPreNode = nullptr;
            // 下一层的头结点
            Node *nextLevelHeadNode = nullptr;

            auto p = Q;
            // 遍历层中的每一个结点
            while (p) {
                // 得到有效的下一层中的结点
                if (p->left) {
                    // 如果前驱结点有效，修改其next指针
                    if (nextLevelPreNode) {
                        nextLevelPreNode->next = p->left;
                    }
                    nextLevelPreNode = p->left;
                    // 设置head指针
                    if (!nextLevelHeadNode) {
                        nextLevelHeadNode = p->left;
                    }
                }

                if (p->right) {
                    // 如果前驱结点有效，修改其next指针
                    if (nextLevelPreNode) {
                        nextLevelPreNode->next = p->right;
                    }
                    nextLevelPreNode = p->right;
                    // 设置head指针
                    if (!nextLevelHeadNode) {
                        nextLevelHeadNode = p->right;
                    }
                }

                p = p->next;
            }
            // 指向下一层的头结点
            Q = nextLevelHeadNode;
        }

        return root;
    }
};
