import java.util.List;

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

// 这里采用分治

class Solution {
    // 将两个合并成一个
    private List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> tmp = new ArrayList<>(a);

        for (Integer x: b) {
            tmp.add(x);
        }

        return tmp;
    }

    // 注意：这里给的区间是左闭右开[b, e)
    private List<List<Integer>> dq(int[] nums, int b, int e) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        if (b >= e) {
            return ans;
        }

        List<Integer> tmp = new ArrayList<>();
        if (b + 1 == e) {
            tmp.add(nums[b]);
            ans.add(tmp);
            return ans;
        }

        // 如果数组里面没有重复元素，那么只需要从中间切分开就可以了
        final int mid = b + ((e-b)>>1);

        List<List<Integer>> l = dq(nums, b, mid);
        List<List<Integer>> r = dq(nums, mid, e);

        // 两两组合
        for (List<Integer> x: l) {
            for (List<Integer> y: r) {
                // 由于我们已经在ans中存放了空集，所以如果遇到两个都是空集的时候
                // 我们就不再进行merge
                if (x.isEmpty() && y.isEmpty()) {
                    continue;
                }
                ans.add(merge(x, y));
            }
        }

        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        final int N = nums == null ? 0 : nums.length;
        return dq(nums, 0, N);
    }
}
// @lc code=end

