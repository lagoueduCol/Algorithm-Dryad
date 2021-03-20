import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (72.11%)
 * Likes:    1225
 * Dislikes: 0
 * Total Accepted:    223.4K
 * Total Submissions: 309.7K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1：
 * 
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 * 
 * 
 * 示例 2：
 * 
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * 
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

    private void backtrack(int[] A, int start, int target, int boxSum, List<Integer> box, List<List<Integer>> ans) {
        final int N = A == null ? 0 : A.length;
        if (boxSum == target) {
            append(box, ans);
        }

        if (boxSum >= target || start >= N) {
            return;
        }

        // 当前这个人可以选择的范围就是[start, N)
        for (int i = start; i < N; i++) {
            box.add(A[i]);
            boxSum += A[i];
            // 当A[]数组里面的元素，不能重复选择的时候，递归的start = i + 1
            // 当A[]里面的元素可以重复选择的时候，递归的start = i
            backtrack(A, i, target, boxSum, box, ans);

            box.remove(box.size() - 1);
            boxSum -= A[i];
        }
    }

    public List<List<Integer>> combinationSum(int[] A, int target) {
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int boxSum = 0;
        backtrack(A, 0, target, boxSum, box, ans);
        return ans;
    }
}
// @lc code=end
