/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前 K 个高频元素
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (61.87%)
 * Likes:    653
 * Dislikes: 0
 * Total Accepted:    136.4K
 * Total Submissions: 220.1K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 * 
 * 
 */

import java.util.*;

// @lc code=start

class Counter extends HashMap<Integer, Integer> {
    public int get(Integer k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(Integer k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

class Solution {
    class Node {
        public int val = 0;
        public int cnt = 0;

        public Node(int a, int b) {
            val = a;
            cnt = b;
        }
    }

    public int[] topKFrequent(int[] A, int k) {
        final int N = A == null ? 0 : A.length;
        if (k <= 0) {
            return new int[0];
        }

        int[] ans = new int[k];

        Counter H = new Counter();

        for (int i = 0; i < N; i++) {
            H.add(A[i], 1);
        }

        Queue<Node> Q = new PriorityQueue<>((v1, v2) -> v1.cnt - v2.cnt);

        for (Map.Entry<Integer, Integer> e : H.entrySet()) {
            Q.add(new Node(e.getKey(), e.getValue()));
            while (Q.size() > k) {
                Q.poll();
            }
        }

        int i = 0;
        while (!Q.isEmpty()) {
            ans[i++] = Q.poll().val;
        }

        Arrays.sort(ans);
        return ans;
    }
}
// @lc code=end
