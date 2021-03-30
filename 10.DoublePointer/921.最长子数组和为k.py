# /*
# 911. 最大子数组之和为k
# 中文
# English
# 给一个数组nums和目标值k，找到数组中最长的子数组，使其中的元素和为k。如果没有，则返回0。
# 
# 样例
# Example1
# 
# Input:  nums = [1, -1, 5, -2, 3], k = 3
# Output: 4
# Explanation:
# because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
# Example2
# 
# Input: nums = [-2, -1, 2, 1], k = 1
# Output: 2
# Explanation:
# because the subarray [-1, 2] sums to 1 and is the longest.
# 挑战
# 能否用O(n)的时间复杂度完成？
# 
# 注意事项
# 数组之和保证在32位有符号整型数的范围内
# */
# 
# // 测试链接: https://www.lintcode.com/problem/maximum-size-subarray-sum-equals-k/leaderboard/

class Solution:
    """
    @param nums: an array
    @param k: a target value
    @return: the maximum length of a subarray that sums to k
    """
    def maxSubArrayLen(self, A, k):
        # Write your code here
        N = 0 if not A else len(A)
        ans = 0

        H = {}
        H[0] = -1

        pre = 0
        for i in range(N):
          pre += A[i]
          want = pre - k

          pos = H.get(want, N + N)
          ans = max(ans, i - pos)

          if -2 ==  H.get(pre, -2):
            H[pre] = i
          
        return ans
