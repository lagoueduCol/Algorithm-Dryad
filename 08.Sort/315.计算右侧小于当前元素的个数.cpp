/*
 * @lc app=leetcode.cn id=315 lang=cpp
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
    vector<int> t;
    vector<int> ans;
    vector<int> idx;

    // 这里我们实际上是在对原数组的下标数组在排序
    //  唯一用到原数组的地方就是在比较两个数大小的时候。
    // 其他时候都用不到。
    void msort(const vector<int> &a, vector<int> &idx, int b, int e) {
        if (b >= e || b + 1 >= e) {
            return;
        }

        const int m = b + ((e-b)>>1);
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

        for (int i = b; i < e; i++) {
            idx[i] = t[i];
        }
    }
public:
    vector<int> countSmaller(vector<int>& nums) {
        t.resize(nums.size());
        ans.resize(nums.size(), 0);
        idx.resize(nums.size());
        // 把idx变成索引[0, 1, 2, 3, ....]
        iota(idx.begin(), idx.end(), 0);
        msort(nums, idx, 0, nums.size());
        return ans;
    }
};

// @lc code=end

