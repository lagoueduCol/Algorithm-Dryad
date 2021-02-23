# -*- coding: utf-8 -*-

class Solution:
  def findSmallSeq(self, nums, k):
    if not nums or len(nums) == 0 or k <= 0:
      return []

    ans = [0] * k
    s = []

    # 这里生成单调栈
    for i in range(0, len(nums)):
      x = nums[i]
      left = len(nums) - i
      # 注意我们想要提取出k个数，所以注意控制扔掉的数的个数
      while len(s) > 0 and (len(s) + left) > k and s[-1] > x:
        s.pop()
      s.append(x)

    # 如果递增栈里面的数太多，那么我们只需要取出前k个就可以了。
    # 多余的栈中的元素需要扔掉。
    while len(s) > k:
      s.pop()

    # 把k个元素取出来，注意这里取的顺序!
    for i in range(k-1, -1, -1):
      ans[i] = s[-1]
      s.pop()

    return ans

# 测试代码
solution = Solution()
assert [1,2,3] == solution.findSmallSeq([9,2,4,5,1,2,6,3,100,4], 3)
assert [1,2] == solution.findSmallSeq([9,2,4,5,1,2,6,3,100,4], 2)
assert [1] == solution.findSmallSeq([9,2,4,5,1,2,6,3,100,4], 1)