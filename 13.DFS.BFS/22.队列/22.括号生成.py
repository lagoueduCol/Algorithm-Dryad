#
# @lc app=leetcode.cn id=22 lang=python
#
# [22] 括号生成
#

# @lc code=start

from Queue import Queue

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
        
        ans = []
        Q = Queue()

        cur = Node()
        cur.path = "("
        cur.lcnt = 1
        cur.rcnt = 0

        Q.put(cur)

        while not Q.empty():
            oldSize = Q.qsize()

            for i in range(oldSize):
                cur = Q.get()

                if len(cur.path) == (n + n) and cur.lcnt == cur.rcnt:
                    ans.append(cur.path)
                    continue

                if cur.lcnt >= cur.rcnt and cur.lcnt < n:
                    next = Node()
                    next.path = cur.path + "("
                    next.lcnt = cur.lcnt + 1
                    next.rcnt = cur.rcnt
                    Q.put(next)

                if cur.lcnt > cur.rcnt:
                    next = Node()
                    next.path = cur.path + ")"
                    next.lcnt = cur.lcnt
                    next.rcnt = cur.rcnt + 1
                    Q.put(next)
        
        return ans

# @lc code=end
