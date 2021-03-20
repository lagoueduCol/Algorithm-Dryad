import java.awt.List;
import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (77.67%)
 * Likes:    1222
 * Dislikes: 0
 * Total Accepted:    277.9K
 * Total Submissions: 357.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 */

// @lc code=start
class Solution {
    private void append(List<Integer> box, List<List<Integer>> ans) {
        ans.add(new ArrayList<>());
        for (Integer x : box) {
            ans.get(ans.size() - 1).add(x);
        }
    }

    private void backtrack(int[] A, int i, boolean[] used, List<Integer> box, List<List<Integer>> ans) {
        final int N = A == null ? 0 : A.length;
        // 如果状态已经满足要求
        if (box.size() == N) {
            append(box, ans);
        }

        // 我们总是从第0个人开始，那么一共有N个元素
        // 那么当到第N个人的时候，已经没有东西可以选了。
        // [N ~ inf) 后面所有的人都没有东西可以选了。
        if (i >= N) {
            return;
        }

        // 第i个人本来是可以选择A[0 .... n)里面的元素
        // 但是需要查看一下used[i]是不是被使用了？
        // 可以认为第i个人的选择函数就是使用以前没有使用过的元素
        for (int j = 0; j < N; j++) {
            if (!used[j]) {
                box.add(A[j]);
                used[j] = true;
                backtrack(A, i + 1, used, box, ans);
                box.remove(box.size() - 1);
                used[j] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] A) {
        final int N = A == null ? 0 : A.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (N == 0) {
            return ans;
        }

        boolean[] used = new boolean[N];
        List<Integer> box = new ArrayList<>();
        backtrack(A, 0, used, box, ans);
        return ans;
    }
}
// @lc code=end
