/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (61.92%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    71.4K
 * Total Submissions: 115.3K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        final int N = nums == null ? 0 : nums.length;

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < (1<<N); i++) {
            List<Integer> subset = new ArrayList<>();

            // 这里在检查每个bit的时候，还要带个逻辑
            // 那就是如果值相同的时候，我们希望是有连续的1bit
            boolean validSubset = true;

            // 我们依次查看每个bit
            for (int j = 0; j < N; j++) {
                // 如果当前值与之前的值是一样的。
                if (j > 0 && nums[j] == nums[j-1]) {

                    // 取出当前bit
                    final int curBit = i & (1<<j);

                    // 取出之前一位bit
                    final int preBit = i & (1<<(j-1));

                    // 如果当前位为1，但是之前位为0
                    // 这种情况是不允许的
                    if (curBit != 0 && preBit == 0) {
                        validSubset = false;
                        break;
                    }
                }

                // 如果这个bit为1，那么被选中
                if ((i & (1<<j)) != 0) {
                    subset.add(nums[j]);
                }
            }

            if (validSubset) {
                ans.add(subset);
            }
        }

        return ans;
    }
}
// @lc code=end

