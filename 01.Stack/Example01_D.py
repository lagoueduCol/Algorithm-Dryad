# -*- coding: utf-8 -*-

class Solution:
    def isValid(self, s):
        # 当字符串本来就是空的时候，我们可以快速返回true
        if not s and len(s) == 0:
            return True
        # 当字符串长度为奇数的时候，不可能是一个有效的合法字符串
        if len(s) % 2 == 1:
            return False

        # 消除法的主要核心逻辑:
        leftBraceNumber = 0
        for c in s:
            # 取出字符
            if c == '(':
                # 如果是'('，那么压栈
                leftBraceNumber += 1
            elif c == ')':
                # 如果是')'，那么就尝试弹栈
                if leftBraceNumber <= 0:
                    # 如果弹栈失败，那么返回false
                    return False
                leftBraceNumber -= 1
        return leftBraceNumber == 0


solution = Solution()

assert solution.isValid("")
assert not solution.isValid("(")
assert not solution.isValid(")")

assert solution.isValid("()")
assert not solution.isValid("((")
assert not solution.isValid("))")
assert not solution.isValid(")(")

assert not solution.isValid("())")
assert not solution.isValid("(((")
assert not solution.isValid(")))")
assert not solution.isValid(")()")

assert solution.isValid("()()")
assert solution.isValid("(())")
assert not solution.isValid("))((")

assert solution.isValid("()()()")
assert solution.isValid("((()))")
assert solution.isValid("()(())")
assert not solution.isValid("()(()(")