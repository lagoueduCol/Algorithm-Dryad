#
# @lc app=leetcode.cn id=373 lang=python
#
# [373] 查找和最小的K对数字
#
# https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/description/
#
# algorithms
# Medium (43.37%)
# Likes:    162
# Dislikes: 0
# Total Accepted:    13.2K
# Total Submissions: 30.5K
# Testcase Example:  '[1,7,11]\n[2,4,6]\n3'
#
# 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
# 
# 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
# 
# 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
# 
# 示例 1:
# 
# 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
# 输出: [1,2],[1,4],[1,6]
# 解释: 返回序列中的前 3 对数：
# ⁠    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
# 
# 
# 示例 2:
# 
# 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
# 输出: [1,1],[1,1]
# 解释: 返回序列中的前 2 对数：
# [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
# 
# 
# 示例 3:
# 
# 输入: nums1 = [1,2], nums2 = [3], k = 3 
# 输出: [1,3],[2,3]
# 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
# 
# 
#

# @lc code=start
import heapq

class Solution(object):
    def kSmallestPairs(self, A, B, k):
        if not A or not B or k <= 0:
            return []
        
        N = 0 if not A else len(A)
        M = 0 if not B else len(B)

        Q = []
        for i in range(N):
            Q.append([A[i] + B[0], i, 0])
        
        heapq.heapify(Q)
        ans = []

        i = 0
        while i < k and len(Q) > 0:
            i += 1

            p = heapq.heappop(Q)
            ans.append([A[p[1]], B[p[2]]])

            if p[2] + 1 < M:
                heapq.heappush(Q, [A[p[1]] + B[p[2]+1], p[1], p[2] + 1])
        
        return ans

    