import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=373 lang=java
 *
 * [373] 查找和最小的K对数字
 *
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/description/
 *
 * algorithms
 * Medium (43.37%)
 * Likes:    162
 * Dislikes: 0
 * Total Accepted:    13.2K
 * Total Submissions: 30.5K
 * Testcase Example:  '[1,7,11]\n[2,4,6]\n3'
 *
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 * 
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 * 
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 * 
 * 示例 1:
 * 
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * ⁠    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 
 * 
 * 示例 3:
 * 
 * 输入: nums1 = [1,2], nums2 = [3], k = 3 
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * 
 * 
 */

/**
 * 可以将这个题看成是一个k个有序数组合并全的问题。 假设A = [1, 2, 3]. B = [4,5,6]
 *
 * 那么可以认为生成了数组
 *
 * [(1,4), (1,5), (1, 6)] [(2,4), (2,5), (2,6)] [(3,4), (3,5), (3,6)]
 *
 */

// @lc code=start
import java.util.List;

class Solution {
    class Node {
        public int i;
        public int j;

        public Node(int a, int b) {
            i = a;
            j = b;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] A, int[] B, int k) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        final int N = A.length;
        final int M = B.length;

        // java小堆
        Queue<Node> Q = new PriorityQueue<>((v1, v2) -> A[v1.i] + B[v1.j] - A[v2.i] - B[v2.j]);

        for (int i = 0; i < N; i++) {
            Q.add(new Node(i, 0));
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k && !Q.isEmpty(); i++) {
            Node p = Q.poll();
            ans.add(Arrays.asList(A[p.i], B[p.j]));
            if (p.j + 1 < M) {
                Q.add(new Node(p.i, p.j + 1));
            }
        }

        return ans;
    }
}
// @lc code=end
