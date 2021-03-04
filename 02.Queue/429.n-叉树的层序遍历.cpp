/*
 * @lc app=leetcode.cn id=429 lang=cpp
 *
 * [429] N 叉树的层序遍历
 *
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (68.07%)
 * Likes:    135
 * Dislikes: 0
 * Total Accepted:    40.1K
 * Total Submissions: 58.8K
 * Testcase Example:  '[1,null,3,2,4,null,5,6]'
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：root =
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树的高度不会超过 1000
 * 树的节点总数在 [0, 10^4] 之间
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
    vector<vector<int>> levelOrder(Node* root) {
        queue<Node *> Q;
        // 注意，入队的时候都不能为空
        if (root) {
            Q.push(root);
        }

        vector<vector<int>> ans;

        while (!Q.empty()) {
            // 生成新的一层
            ans.emplace_back();
            // 拿到当前层的结点个数
            int qSize = Q.size();

            // 依次取出当前层的结点
            while (qSize--) {
                // 照顺序取出当前层的结点
                auto cur = Q.front();
                Q.pop();
                // 把结点值放到当前层
                ans.back().push_back(cur->val);
                for (auto t: cur->children) {
                    if (t) {
                        Q.push(t);
                    }
                }
            }
        }

        // 返回结果
        return ans;
    }
};
// @lc code=end

