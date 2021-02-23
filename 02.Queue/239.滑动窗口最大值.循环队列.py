#
#
# [239] 滑动窗口最大值
#
# https://leetcode-cn.com/problems/sliding-window-maximum/description/
#
# algorithms
# Hard (49.00%)
# Likes:    670
# Dislikes: 0
# Total Accepted:    93K
# Total Submissions: 189.7K
# Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
#
# 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
# 个数字。滑动窗口每次只向右移动一位。
# 
# 返回滑动窗口中的最大值。
# 
# 
# 
# 进阶：
# 
# 你能在线性时间复杂度内解决此题吗？
# 
# 
# 
# 示例:
# 
# 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
# 输出: [3,3,5,5,6,7] 
# 解释: 
# 
# ⁠ 滑动窗口的位置                最大值
# ---------------               -----
# [1  3  -1] -3  5  3  6  7       3
# ⁠1 [3  -1  -3] 5  3  6  7       3
# ⁠1  3 [-1  -3  5] 3  6  7       5
# ⁠1  3  -1 [-3  5  3] 6  7       5
# ⁠1  3  -1  -3 [5  3  6] 7       6
# ⁠1  3  -1  -3  5 [3  6  7]      7
# 
# 
# 
# 提示：
# 
# 
# 1 <= nums.length <= 10^5
# -10^4 <= nums[i] <= 10^4
# 1 <= k <= nums.length
# 
# 
#


class Queue(object):
    def __init__(self, cap):
        self.cap = cap
        self.head = 0
        self.tail = 0
        self.used = 0
        self.q = [0] * cap

    def backIndex(self):
        return (self.tail - 1 + self.cap) % self.cap

    def push(self, val):
        while self.used > 0 and self.q[self.backIndex()] < val:
            self.tail = self.backIndex()
            self.used -= 1
        self.q[self.tail] = val
        self.tail = (self.tail + 1) % self.cap
        self.used += 1
    
    def pop(self, val):
        if self.used > 0 and self.q[self.head] == val:
            self.head = (self.head + 1) % self.cap
            self.used -= 1
    
    def front(self):
        return self.q[self.head]


class Solution(object):
    def maxSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        if not nums or len(nums) < k:
            return []
        
        Q = Queue(k + 1)

        ans = []
        for i in range(0, len(nums)):
            Q.push(nums[i])
            if i < k - 1:
                continue
        
            ans.append(Q.front())

            Q.pop(nums[i-k+1])
        
        return ans


