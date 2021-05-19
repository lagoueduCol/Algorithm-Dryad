/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (69.26%)
 * Likes:    931
 * Dislikes: 0
 * Total Accepted:    165.7K
 * Total Submissions: 238.9K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 
 * 返回如下的二叉树：
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * 函数功能： 
     * 
     * 输入：前序preorder[]数组的[b, e)这个范围
     *      以及inorder[]数组的[f, t)这个范围
     *      b是begin的缩写，e是end的缩写
     *      f是from的缩写，t是to的缩写
     *
     * 返回一棵二叉树
     *
     *
     * 参数:
     *      - preorder 原始二叉树的前序遍历数组
     *        [b, e)   preorder[b...e)范围
     *      - inorder  原始二叉树的中序遍历数组
     *        [f, t)   inorder[f, t)范围
     *
     * 返回值：
     *      - 二叉树的根结点
     *
     * 限制条件：
     *      - 数组中没有重复的元素
     */
    private TreeNode createTree(int[] preorder,
                                int b,
                                int e,
                                int[] inorder,
                                int f,
                                int t) {
        if (b >= e) {
            return null;
        }

        // 如果只有一个结点
        if (b + 1 == e) {
            return new TreeNode(preorder[b]);
        }

        // 利用根结点来切分中序
        final int rootValue = preorder[b];

        // 找到根结点在中序遍历中的位置
        final int rootPos = findPos(inorder, f, t, rootValue);

        // 创建根结点
        TreeNode root = new TreeNode(rootValue);

        // 利用在中序遍历中找到的根结点，将数组分为三部分
        // 分别计算出左子树与右子树的长度
        final int leftLen = rootPos - f;
        final int rightLen = t - rootPos - 1;

        // 左子树
        // preorder里面左子树的范围 => [b + 1, b + 1 + leftLlen)
        // inorder里面左子树的范围  => [f, rootPos)
        root.left = createTree(preorder,
                               b + 1,
                               b + 1 + leftLen,
                               inorder,
                               f,
                               rootPos);

        // 右子树
        // preorder右子树的范围 => [b + 1 + leftLen , e)
        // inorder里面右子树的范围 => [rootPos + 1, t]
        root.right = createTree(preorder,
                                b + 1 + leftLen,
                                e,
                                inorder,
                                rootPos + 1,
                                t);
        return root;
    }

    private int findPos(int[] inorder, int f, int t, int val) {
        for (int i = f; i < t; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        final int N = preorder == null ? 0 : preorder.length;

        if (N == 0) {
            return null;
        }

        return createTree(preorder, 0, N, inorder, 0, N);
    }
}
// @lc code=end



void TEST_null() {
  assert null == buildTree(null, null);
}

void TEST_length0() {
  int[] preorder = new int[0];
  int[] inorder = new int[0];
  assert null == buildTree(preorder, inorder);
}

void TEST_single() {
  int[] preorder = new int[] { 1 };
  int[] inorder = new int[] { 1 };
  TreeNode ret = buildTree(preorder, inorder);
  assert null != ret;
  assert ret.val == 1;
  assert ret.left == null;
  assert ret.right == null;
}

void TEST_two() {
  int[] preorder = new int[] { 1, 2 };
  int[] inorder = new int[] { 1, 2 };

  TreeNode ret = buildTree(preorder, inorder);
  assert null != ret;
  assert 1 == ret.val;
  assert 2 == ret.right.val;
}

