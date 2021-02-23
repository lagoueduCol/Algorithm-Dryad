// 测试平台链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/submissions/
// LeetCode 102
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        // 初始化当前层结点
        List<TreeNode> curLevel = new ArrayList<>();
        // 注意：需要root不空的时候才加到里面。
        if (root != null) {
            curLevel.add(root);
        }

        while (curLevel.size() > 0) {
            // 准备用来存放下一层的结点
            List<TreeNode> nextLevel = new ArrayList<>();
            // 用来存放当前层的结果
            List<Integer> curResult = new ArrayList<>();

            // 遍历当前层的每个结点
            for (TreeNode cur: curLevel) {
                // 把当前层的值存放到当前结果里面
                curResult.add(cur.val);

                // 生成下一层
                if (cur.left != null) {
                    nextLevel.add(cur.left);
                }
                if (cur.right != null) {
                    nextLevel.add(cur.right);
                }
            }

            // 注意这里的更迭!
            curLevel = nextLevel;

            ans.add(curResult);
        }

        return ans;
    }
}
