import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (64.04%)
 * Likes:    528
 * Dislikes: 0
 * Total Accepted:    139.9K
 * Total Submissions: 218.9K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
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
            if (i > start && A[i] == A[i - 1]) {
                continue;
            }
            box.add(A[i]);
            boxSum += A[i];
            // 当A[]数组里面的元素，不能重复选择的时候，递归的start = i + 1
            // 当A[]里面的元素可以重复选择的时候，递归的start = i
            backtrack(A, i + 1, target, boxSum, box, ans);

            box.remove(box.size() - 1);
            boxSum -= A[i];
        }
    }

    public List<List<Integer>> combinationSum2(int[] A, int target) {
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int boxSum = 0;
        Arrays.sort(A);
        backtrack(A, 0, target, boxSum, box, ans);
        return ans;
    }
}

// @lc code=end
