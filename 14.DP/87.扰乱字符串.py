#
# @lc app=leetcode.cn id=87 lang=python
#
# [87] 扰乱字符串
#
# https://leetcode-cn.com/problems/scramble-string/description/
#
# algorithms
# Hard (48.53%)
# Likes:    198
# Dislikes: 0
# Total Accepted:    18.3K
# Total Submissions: 37.8K
# Testcase Example:  '"great"\n"rgeat"'
#
# 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
# 
# 如果字符串的长度为 1 ，算法停止
# 如果字符串的长度 > 1 ，执行下述步骤：
# 
# 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y
# 。
# 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x
# 。
# 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
# 
# 
# 
# 
# 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：s1 = "great", s2 = "rgeat"
# 输出：true
# 解释：s1 上可能发生的一种情形是：
# "great" --> "gr/eat" # 在一个随机下标处分割得到两个子字符串
# "gr/eat" --> "gr/eat" # 随机决定：「保持这两个子字符串的顺序不变」
# "gr/eat" --> "g/r / e/at" # 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
# "g/r / e/at" --> "r/g / e/at" # 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
# "r/g / e/at" --> "r/g / e/ a/t" # 继续递归执行此算法，将 "at" 分割得到 "a/t"
# "r/g / e/ a/t" --> "r/g / e/ a/t" # 随机决定：「保持这两个子字符串的顺序不变」
# 算法终止，结果字符串和 s2 相同，都是 "rgeat"
# 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
# 
# 
# 示例 2：
# 
# 
# 输入：s1 = "abcde", s2 = "caebd"
# 输出：false
# 
# 
# 示例 3：
# 
# 
# 输入：s1 = "a", s2 = "a"
# 输出：true
# 
# 
# 
# 
# 提示：
# 
# 
# s1.length == s2.length
# 1 
# s1 和 s2 由小写英文字母组成
# 
# 
#

# @lc code=start
class Solution(object):
    def buildRow(self, N):
        ret = []
        for x in range(N):
            ret.append(False)
        return ret

    def buildMatrix(self, N):
        ret = []
        for r in range(N):
            ret.append(self.buildRow(N))
        return ret

    def buildDP(self, N):
        ret = []
        for l in range(N):
            ret.append(self.buildMatrix(N))
        return ret

    def isScramble(self, s1, s2):
        s1len = 0 if not s1 else len(s1)
        s2len = 0 if not s2 else len(s2)

        if s1len != s2len:
            return False
        
        N = s1len
        dp = self.buildDP(N + 1)

        # 初始条件是长度为1的情况
        for s1start in range(N):
            for s2start in range(N):
                dp[s1start][s2start][1] = s1[s1start] == s2[s2start]

        # 那么再通过递推公式计算其他长度的情况
        # 子串的截取，这里我们采用开闭原则[start, end)
        # 也就是说，end是可以取到N的。
        for xlen in range(2, N + 1):
            for s1start in range(0, N - xlen + 1):
                for s2start in range(0, N - xlen + 1):
                    # 现在我们需要计算两个子串
                    # X = s1[s1start, s1start+len)
                    # Y = s2[s2start, s2start+len)
                    # 这两个子串是否是扰动字符串
                    # 那么根据递推公式，我们需要找切分点
                    # 切分子串的时候
                    # X 切分为 X = a + b, 分为左右两半
                    # Y 切分为 Y = c + d，同样分为左右两半
                    # 左边的长度为leftLen, 右边的长度就是len - leftLen
                    for leftLen in range(1, xlen):
                        if dp[s1start][s2start][xlen]:
                            break
                        # 第一种切分，case 1
                        # X = a + b, Y = c + d
                        # [s1start, s1start + leftLen) <- a
                        # [s2start, s2start + leftLen) <- c
                        #
                        # [s1start + leftLen, s1start + len) <- b
                        # [s2start + leftLen, s2start + len) <- d
                        c1 = dp[s1start][s2start][leftLen] and \
                            dp[s1start+leftLen][s2start+leftLen][xlen-leftLen]
                        # 第2种切分
                        # X = a + b, Y = c + d
                        # [s1start, s1start + leftLen) <- a
                        # [s2start + len - leftLen, s2start + len) <- d
                        #
                        # [s1start + leftLen, s1start + len) <- b
                        # [s2start, s2start + len - leftLen) <- c
                        c2 = dp[s1start][s2start+xlen-leftLen][leftLen] and \
                             dp[s1start + leftLen][s2start][xlen - leftLen]
                        
                        dp[s1start][s2start][xlen] = c1 or  c2

        return dp[0][0][N]

# @lc code=end

