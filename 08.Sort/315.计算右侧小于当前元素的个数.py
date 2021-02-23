#
# @lc app=leetcode.cn id=315 lang=python
#
# [315] 计算右侧小于当前元素的个数
#
# https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/description/
#
# algorithms
# Hard (42.09%)
# Likes:    520
# Dislikes: 0
# Total Accepted:    40.2K
# Total Submissions: 95.5K
# Testcase Example:  '[5,2,6,1]'
#
# 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于
# nums[i] 的元素的数量。
# 
# 
# 
# 示例：
# 
# 输入：nums = [5,2,6,1]
# 输出：[2,1,1,0] 
# 解释：
# 5 的右侧有 2 个更小的元素 (2 和 1)
# 2 的右侧仅有 1 个更小的元素 (1)
# 6 的右侧有 1 个更小的元素 (1)
# 1 的右侧有 0 个更小的元素
# 
# 
# 
# 
# 提示：
# 
# 
# 0 <= nums.length <= 10^5
# -10^4 <= nums[i] <= 10^4
# 
# 
#

# @lc code=start
class Solution(object):
    def countSmaller(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if not nums or len(nums) == 0:
            return []

        t = [0] * len(nums)
        idx = range(0, len(nums))
        ans = [0] * len(nums)

        def msort(a, b, e, t, idx, ans):
            if b >= e or b + 1 >= e:
                return

            m = b + ((e-b)>>1)
            msort(a, b, m, t, idx, ans)
            msort(a, m, e, t, idx, ans)

            i = b
            j = m
            to = b

            while i < m or j < e:
                if j >= e or (i < m and a[idx[i]] <= a[idx[j]]):
                    ans[idx[i]] += j - m
                    t[to] = idx[i]
                    to += 1
                    i += 1
                else:
                    t[to] = idx[j]
                    to += 1
                    j += 1

            for i in range(b, e):
                idx[i] = t[i]

        msort(nums, 0, len(nums), t, idx, ans)

        return ans
# @lc code=end

