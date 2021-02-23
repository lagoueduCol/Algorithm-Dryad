#
# @lc app=leetcode.cn id=904 lang=python
#
# [904] 水果成篮
#
# https://leetcode-cn.com/problems/fruit-into-baskets/description/
#
# algorithms
# Medium (43.31%)
# Likes:    71
# Dislikes: 0
# Total Accepted:    10.5K
# Total Submissions: 24.3K
# Testcase Example:  '[1,2,1]'
#
# 在一排树中，第 i 棵树产生 tree[i] 型的水果。
# 你可以从你选择的任何树开始，然后重复执行以下步骤：
# 
# 
# 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
# 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
# 
# 
# 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
# 
# 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
# 
# 用这个程序你能收集的水果树的最大总量是多少？
# 
# 
# 
# 示例 1：
# 
# 输入：[1,2,1]
# 输出：3
# 解释：我们可以收集 [1,2,1]。
# 
# 
# 示例 2：
# 
# 输入：[0,1,2,2]
# 输出：3
# 解释：我们可以收集 [1,2,2]
# 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
# 
# 
# 示例 3：
# 
# 输入：[1,2,3,2,2]
# 输出：4
# 解释：我们可以收集 [2,3,2,2]
# 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
# 
# 
# 示例 4：
# 
# 输入：[3,3,3,1,2,1,1,2,3,3,4]
# 输出：5
# 解释：我们可以收集 [1,2,1,1,2]
# 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。
# 
# 
# 
# 
# 提示：
# 
# 
# 1 <= tree.length <= 40000
# 0 <= tree[i] < tree.length
# 
# 
#

# @lc code=start
class Solution(object):
    def totalFruit(self, A):
        """
        :type A: List[int]
        :rtype: int
        """

        N = len(A) if A else 0
        
        left = -1
        ans = 0

        # 记录区间中元素的个数
        H = {}

        for i in range(0, N):
            x = A[i]

            H[x] = H.get(x, 0) + 1

            # 如果新进来的元素破坏了状态
            # 那么这里需要移动左指针
            while len(H) > 2:
                left += 1
                old = A[left]
                H[old] = H[old] - 1
                if H[old] == 0:
                    del H[old]
            
            # 到这里时，得到的区间肯定是一个有效区间
            ans = max(ans, i - left)
        
        return ans

        
# @lc code=end

