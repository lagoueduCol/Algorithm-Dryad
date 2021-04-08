#
# @lc app=leetcode.cn id=22 lang=python
#
# [22] 括号生成
#

# @lc code=start


class Solution(object):
    def backtrace(self, n, idx, lcnt, rcnt, path, ans):
        if idx == (n + n):
            if lcnt == rcnt:
                ans.append(path)
        else:
            if lcnt >= rcnt and lcnt < n:
                path = path + '('
                self.backtrace(n, idx + 1, lcnt + 1, rcnt, path, ans)
                path = path[:-1]

            if lcnt > rcnt:
                path = path + ')'
                self.backtrace(n, idx + 1, lcnt, rcnt + 1, path, ans)
                path = path[:-1]

    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """

        if n <= 0:
            return []

        ans = []
        path = ""

        self.backtrace(n, 0, 0, 0, path, ans)

        return ans
# @lc code=end
