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
        Arrays.sort(nums);

        List<List<Integer>> cur = new ArrayList<>();
        // cur里面会有一个空集
        cur.add(new ArrayList<>());

        List<List<Integer>> next = new ArrayList<>();

        // 前面用来更新的元素的值
        int pre = Integer.MAX_VALUE;

        // 在利用pre元素更新之前
        // cur里面item的个数
        // 注意：是利用pre更新之前，不是pre更新之后的元素的个数!
        int beforePreUpdateSize = 0;

        for (int curValue: nums) {
            next.clear();

            // 在更新之前，我们需要判断一下，是从cur的哪里开始更新
            // 正常情况下，都是从cur[0]开始更新
            int startUpdatePos = 0;

            // 如果与pre值相等
            if (curValue == pre) {
                // 那么在更新的时候，需要从beforePreUpdateSize这里开始更新。
                // 因为[0, ...., beforePreUpdateSize)这部分内容
                // 肯定已经被等于pre的值给更新过了
                startUpdatePos = beforePreUpdateSize;
            }

            for (int i = startUpdatePos; i < cur.size(); i++) {
                List<Integer> newSubset = new ArrayList<>(cur.get(i));
                newSubset.add(curValue);
                next.add(newSubset);
            }

            // 记录一下更新前的大小
            int beforeCurValueUpdateSize = cur.size();
            
            for (List<Integer> subset: next) {
                cur.add(subset);
            }

            // 更新一下大小的情况
            beforePreUpdateSize = beforeCurValueUpdateSize;
            pre = curValue;
        }

        return cur;
    }
}
// @lc code=end

