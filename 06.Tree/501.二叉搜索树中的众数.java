import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=501 lang=java
 *
 * [501] 二叉搜索树中的众数
 *
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (50.05%)
 * Likes:    279
 * Dislikes: 0
 * Total Accepted:    49.3K
 * Total Submissions: 98.4K
 * Testcase Example:  '[1,null,2,2]'
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 
 * 假定 BST 有如下定义：
 * 
 * 
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 
 * 
 * 例如：
 * 给定 BST [1,null,2,2],
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  2
 * 
 * 
 * 返回[2].
 * 
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    private int preCnt = 0;
    private int preValue = Integer.MAX_VALUE;
    private int ansCnt = 0;

    private void midOrder(TreeNode root, List<Integer> ans) {
        if (root != null) {

            midOrder(root.left, ans);

            if (preValue == root.val) {
                preCnt++;
            } else {
                preValue = root.val;
                preCnt = 1;
            }

            if (ans != null) {
                if (preCnt == ansCnt) {
                    ans.add(preValue);
                }
            }
            ansCnt = Math.max(ansCnt, preCnt);

            midOrder(root.right, ans);
        }

    }

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        midOrder(root, null);

        preCnt = 0;
        List<Integer> ans = new ArrayList<>();
        midOrder(root, ans);

        return ans.stream().mapToInt(i -> i).toArray();
    }
}
// @lc code=end