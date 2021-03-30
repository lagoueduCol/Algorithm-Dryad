# // 64. 字符流中第一个只出现一次的字符
# /*
# 请实现一个函数用来找出字符流中第一个只出现一次的字符。
#
# 例如，当从字符流中只读出前两个字符”go”时，第一个只出现一次的字符是’g’。
#
# 当从该字符流中读出前六个字符”google”时，第一个只出现一次的字符是’l’。
#
# 如果当前字符流没有存在出现一次的字符，返回#字符。
#
# 输入："google"
#
# 输出："ggg#ll"
# 解释：每当字符流读入一个字符，就进行一次判断并输出当前的第一个只出现一次的字符。
#
# */
# // https://www.acwing.com/problem/content/60/


class Solution:
    def __init__(self):
        self.s = ""
        self.left = -1
        self.cnt = [0]*256 
        self.i = -1
        
    def insert(self, char):
        """
        :type char: str
        :rtype: void
        """
        self.i += 1
        self.s += char
        self.cnt[ord(char)] += 1
        while self.left < self.i and self.cnt[ord(self.s[1 + self.left])] > 1:
            self.left += 1

    def firstAppearingOnce(self):
        """
        :rtype: str
        """
        if self.left >= self.i:
            return '#'
        return self.s[self.left+1] 