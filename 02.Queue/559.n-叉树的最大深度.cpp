/*
 * @lc app=leetcode.cn id=559 lang=cpp
 *
 * [559] N 叉树的最大深度
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/description/
 *
 * algorithms
 * Easy (71.45%)
 * Likes:    152
 * Dislikes: 0
 * Total Accepted:    43.7K
 * Total Submissions: 61.2K
 * Testcase Example:  '[1,null,3,2,4,null,5,6]'
 *
 * 给定一个 N 叉树，找到其最大深度。
 * 
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * 
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root =
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 10^4] 之间。
 * 
 * 
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    int maxDepth(Node* root) {
        queue<Node *> Q;
        // 注意，入队的时候都不能为空
        if (root) {
            Q.push(root);
        }
        int depth = 0;

        while (!Q.empty()) {
            // 拿到当前层的结点个数
            int qSize = Q.size();
            // 依次取出当前层的结点
            while (qSize--) {
                // 照顺序取出当前层的结点
                auto cur = Q.front();
                Q.pop();
                for (auto t: cur->children) {
                    if (t) {
                        Q.push(t);
                    }
                }
            }
            depth++;
        }
        return depth;
    }
};
// @lc code=end

