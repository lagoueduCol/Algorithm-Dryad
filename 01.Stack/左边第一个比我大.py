
class LeftLarge:
  def findLeftLarge(self, A):
    if not A or len(A) == 0:
      return []

    # 结果数组
    ans =[0] * len(A)

    # 注意，栈中的元素记录的是下标
    t = []

    for i in range(len(A)-1, -1, -1):
      x = A[i]
      # 每个元素都向左遍历栈中的元素完成消除动作
      while len(t) > 0 and A[t[-1]] < x:
        # 消除的时候，记录一下被谁消除了
        ans[t[-1]] = i
        # 消除时候，值更大的需要从栈中消失
        t.pop()

      # 剩下的入栈
      t.append(i)

    # 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
    while len(t) > 0:
      ans[t[-1]] = -1
      t.pop()

    return ans
