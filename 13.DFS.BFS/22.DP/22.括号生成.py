#
# @lc app=leetcode.cn id=22 lang=python
#
# [22] 括号生成
#

# @lc code=start


class Solution(object):
    GG = [[""]]

    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """

        G = self.GG

        if n <= 0:
            return []

        while len(G) <= n:
            cur = []
            for j in range(len(G)):
                l = G[j]
                r = G[len(G)-j-1]
                for x in l:
                    for y in r:
                        cur.append('(' + x + ')' + y)
            G.append(cur)

        return G[n]

# @lc code=end
