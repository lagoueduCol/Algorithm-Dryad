/*
 * @lc app=leetcode.cn id=315 lang=java
 *
 * [315] 计算右侧小于当前元素的个数
 *
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (42.09%)
 * Likes:    520
 * Dislikes: 0
 * Total Accepted:    40.2K
 * Total Submissions: 95.5K
 * Testcase Example:  '[5,2,6,1]'
 *
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于
 * nums[i] 的元素的数量。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0] 
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start

class Solution {
    private int[] t = null;
    private int[] ans = null;
    private int[] idx = null;

    private void msort(final int[] a, int[] idx, int b, int e) {
        if (b >= e || b + 1 >= e) {
            return;
        }

        final int m = b + ((e-b)>>1);
        msort(a, idx, b, m);
        msort(a, idx, m, e);

        int i = b;
        int j = m;
        int to = b;

        while (i < m || j < e) {
            if (j >= e || i < m && a[idx[i]] <= a[idx[j]]) {
                ans[idx[i]] += j - m;
                t[to++] = idx[i++];
            } else {
                t[to++] = idx[j++];
            }
        }

        for (i = b; i < e; i++) {
            idx[i] = t[i];
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || 0 == nums.length) {
            return new ArrayList<>();
        }

        final int n = nums.length;
        // 临时缓存数组
        t = new int[n];
        ans = new int[n];

        idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        msort(nums, idx, 0, n);

        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }
}

// @lc code=end

