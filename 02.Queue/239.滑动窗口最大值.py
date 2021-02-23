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

from collections import deque

class Solution(object):
    def __init__(self):
        # 单调队列使用双端队列来实现
        self.Q = deque()
    
    def push(self, val):
        """
        # 入队的时候，last方向入队，但是入队的时候
        # 需要保证整个队列的数值是单调的
        # (在这个题里面我们需要是递减的)
        # 并且需要注意，这里是Q[-1] < val
        """
        while self.Q and self.Q[-1] < val:
            self.Q.pop()
        # 将元素入队
        self.Q.append(val)

    def pop(self, val):
        # 出队的时候，要相等的时候才会出队
        if self.Q and self.Q[0] == val:
            self.Q.popleft()

    def maxSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        ans = []
        for i in range(0, len(nums)):
            self.push(nums[i])
            # 如果队列中的元素还少于k个
            # 那么这个时候，还不能去取最大值
            if i < k - 1:
                continue
            # 队首元素就是最大值
            ans.append(self.Q[0])
            # 尝试去移除元素
            self.pop(nums[i-k+1])
        return ans



