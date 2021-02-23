// 测试平台链接 https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
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
        // 生成FIFO队列
        Queue<TreeNode> Q = new LinkedList<>();
        // 如果结点不为空，那么加入到FIFO队列
        if (root != null) {
            Q.offer(root);
        }
        // ans用于保存层次遍历的结果
        List<List<Integer>> ans = new LinkedList<>();
        // 开始利用FIFO队列进行层次遍历
        while (Q.size() > 0) {
            // 取出当前层里面元素的个数
            final int qSize = Q.size();
            // 当前层的结果存放于tmp链表中
            List<Integer> tmp = new LinkedList<>();
            // 遍历当前层的每个结点
            for (int i = 0; i < qSize; i++) {
                // 当前层前面的结点先出队
                TreeNode cur = Q.poll();
                // 把结果存放当于当前层中
                tmp.add(cur.val);
                // 把下一层的结点入队，注意入队时需要非空才可以入队。
                if (cur.left != null) {
                    Q.offer(cur.left);
                }
                if (cur.right != null) {
                    Q.offer(cur.right);
                }
            }
            // 把当前层的结果放到返回值里面。
            ans.add(tmp);
        }
        return ans;
    }
}
