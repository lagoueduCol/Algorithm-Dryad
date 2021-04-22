#
# @lc app=leetcode.cn id=90 lang=python
#
# [90] 子集 II
#
# https://leetcode-cn.com/problems/subsets-ii/description/
#
# algorithms
# Medium (61.92%)
# Likes:    416
# Dislikes: 0
# Total Accepted:    71.4K
# Total Submissions: 115.3K
# Testcase Example:  '[1,2,2]'
#
# 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
# 
# 说明：解集不能包含重复的子集。
# 
# 示例:
# 
# 输入: [1,2,2]
# 输出:
# [
# ⁠ [2],
# ⁠ [1],
# ⁠ [1,2,2],
# ⁠ [2,2],
# ⁠ [1,2],
# ⁠ []
# ]
# 
#

# @lc code=start
class Solution(object):
    def merge(self, x, y):
        ans = [v for v in x]
        for v in y:
            ans.append(v)
        return ans

    def dq(self, nums, b, e):
        ans = [[]]

        if (b >= e):
            return ans

        if (b + 1 == e):
            ans.append([nums[b]])
            return ans

        mid = b + ((e-b) >> 1)
        l = mid - 1
        while (l >= b and nums[l] == nums[mid]):
            l -= 1

        r = mid + 1
        while (r < e and nums[r] == nums[mid]):
            r += 1

        # (l, r)这个区间里面的值都是nums[mid]
        if (l == b - 1 and r == e):
            # 整个区间都是一样的值
            tmp = []
            for j in range(e-b):
                tmp.append(nums[b])
                ans.append([x for x in tmp])
            return ans

        # 否则要把数据按整块的切割到一边
        # 正常情况下，我们都取r
        cutPos = r
        # 但是如果r == e
        if (r == e):
            cutPos = l + 1

        lans = self.dq(nums, b, cutPos)
        rans = self.dq(nums, cutPos, e)

        # 需要对这两边的答案进行两两合并
        for x in lans:
            for y in rans:
                if len(x) == 0 and len(y) == 0:
                    continue
                ans.append(self.merge(x,y))

        return ans

    def subsetsWithDup(self, nums):
        nums = sorted(nums)
        return self.dq(nums, 0, len(nums))
# @lc code=end

