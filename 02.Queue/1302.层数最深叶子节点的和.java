/*
 * @lc app=leetcode.cn id=1302 lang=java
 *
 * [1302] 层数最深叶子节点的和
 *
 * https://leetcode-cn.com/problems/deepest-leaves-sum/description/
 *
 * algorithms
 * Medium (81.31%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    14.2K
 * Total Submissions: 17.4K
 * Testcase Example:  '[1,2,3,4,5,null,6,7,null,null,null,null,8]'
 *
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        // 生成FIFO队列
        Queue<TreeNode> Q = new LinkedList<>();
        // 如果结点不为空，那么加入到FIFO队列
        if (root != null) {
            Q.offer(root);
        }
        // ans用于保存结果
        int ans = 0;
        // 开始利用FIFO队列进行层次遍历
        while (Q.size() > 0) {
            // 取出当前层里面元素的个数
            final int qSize = Q.size();
            // 当前层的和存放在tmp里面。
            int tmp = 0;
            // 遍历当前层的每个结点
            for (int i = 0; i < qSize; i++) {
                // 当前层前面的结点先出队
                TreeNode cur = Q.poll();
                // 把结果存放当于当前层中
                tmp += cur.val;
                // 把下一层的结点入队，注意入队时需要非空才可以入队。
                if (cur.left != null) {
                    Q.offer(cur.left);
                }
                if (cur.right != null) {
                    Q.offer(cur.right);
                }
            }
            // 把当前层的结果放到返回值里面。
            ans = tmp;
        }
        return ans;
    }
}
// @lc code=end

