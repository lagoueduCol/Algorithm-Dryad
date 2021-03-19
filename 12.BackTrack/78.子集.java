/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (79.61%)
 * Likes:    1061
 * Dislikes: 0
 * Total Accepted:    212.1K
 * Total Submissions: 266.5K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10 
 * nums 中的所有元素 互不相同
 * 
 * 
 */

// @lc code=start

class Solution {
    void append(List<Integer> box, List<List<Integer>> all) {
        all.add(new ArrayList<>());
        for (Integer x : box) {
            all.get(all.size() - 1).add(x);
        }
    }

    void backTrace(int[] A, int start, List<Integer> box, List<List<Integer>> all) {
        final int N = A == null ? 0 : A.length;

        // 公布当前箱子的状态
        append(box, all);

        // 如果我是最后一个人，并且没有东西给我选了
        // 那么原样返回箱子
        if (start >= N) {
            return;
        }

        // 我还是有宝石可以选择的。
        for (int j = start; j < N; j++) {
            box.add(A[j]);
            backTrace(A, j + 1, box, all);
            box.remove(box.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] A) {
        final int N = A == null ? 0 : A.length;
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(A, 0, box, ans);
        return ans;
    }
}

// @lc code=end
