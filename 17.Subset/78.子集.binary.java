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

// 这里利用二进制串来生成子集
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        final int N = nums == null ? 0 : nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < (1<<N); i++) {

            List<Integer> subset = new ArrayList<>();
            // 看一下哪些元素会被选择
            for (int j = 0; j < N; j++) {
                // 如果要选择nums[j]
                final int mask = 1 << j;
                if ((i & mask) != 0) {
                    subset.add(nums[j]);
                }
            }

            // 然后把tmp 加到 ans里面 
            ans.add(subset);
        }

        return ans;
    }
}
// @lc code=end

