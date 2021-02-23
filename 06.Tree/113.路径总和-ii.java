/*
 *
 * [113] 路径总和 II
 *
 * https://leetcode-cn.com/problems/path-sum-ii/description/
 * https://www.lintcode.com/problem/path-sum-ii/description
 *
 * algorithms
 * Medium (61.33%)
 * Likes:    401
 * Dislikes: 0
 * Total Accepted:    109K
 * Total Submissions: 177.8K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \    / \
 * ⁠       7    2  5   1
 * 
 * 
 * 返回:
 * 
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
 * 
 * 
 */

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
    private List<List<Integer>> ans = null;

    private void backtrace(TreeNode root, List<Integer> path, int sum, int target) {
        // 如果已经是空树，那么没有必要看
        if (root == null) {
            return;
        }

        // 前序遍历，加上累计的和
        sum += root.val;
        // 将结点添加到路径中，相当于压栈一样
        path.add(root.val);
        if (root.left == null && root.right == null) {
            // 如果已经形成了一个有效路径!
            if (sum == target) {
                // 添加到ans中
                ans.add(new ArrayList<>(path));
            }
        } else {
            // 回溯，分别再看子情况。
            backtrace(root.left, path, sum, target);
            backtrace(root.right, path, sum, target);
        }

        // 函数结束的时候弹栈，也要把结点从路径最后扔掉!
        path.remove(path.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrace(root, path, 0, sum);
        return ans;
    }
}

