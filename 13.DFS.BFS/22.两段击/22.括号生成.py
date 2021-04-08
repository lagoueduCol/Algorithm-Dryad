#
# @lc app=leetcode.cn id=22 lang=python
#
# [22] 括号生成
#

# @lc code=start


class Node:
    def __init__(self):
        self.path = ""
        self.lcnt = 0
        self.rcnt = 0


class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """

        if n <= 0:
            return []

        c = Node()
        c.path = '('
        c.lcnt = 1
        c.rcnt = 0

        cur = [c]

        ans = []

        while len(cur) > 0:
            tmp = []
            for c in cur:
                if len(c.path) == (n+n) and c.lcnt == c.rcnt:
                    ans.append(c.path)
                    continue

                if c.lcnt >= c.rcnt and c.lcnt < n:
                    next = Node()
                    next.path = c.path + '('
                    next.lcnt = c.lcnt + 1
                    next.rcnt = c.rcnt
                    tmp.append(next)

                if c.lcnt > c.rcnt:
                    next = Node()
                    next.path = c.path + ')'
                    next.rcnt = c.rcnt + 1
                    next.lcnt = c.lcnt
                    tmp.append(next)

            cur = tmp

        return ans
# @lc code=end
