# -*- coding: utf-8 -*-
# 测试平台 : https://www.acwing.com/problem/content/3/

# your code here
class Solution:
  def Solve(self, N, V, v, w):
    # dp[x]表示当占用掉x空间的容量时
    # 能够得到的最大的收益
    dp = [0] * (V + 1)

    # 可以认为一开始的点集为S0 = {0/*占用的空间*/, 0/*收益*/}
    # 也就是dp[0] = 0
    # 但是这里不需要去设置，省略!

    ans = 0
    for i in range(N):
      # 我们遍历旧有的点集
      # 注意遍历的方向
      for space in range(v[i], V + 1):
        oldSpace = space - v[i]
        newSpace = space

        # 当我们选择<v[i], w[i]>累加到
        # <oldSpace, dp[oldSpace]>的时候
        # 我们可以得到
        # <newSpace, dp[newSpace] = dp[oldSpace] + w[i]>
        # 当然，这里我们dp[newSpace]
        # 可能原来就有值
        # 我们需要取个更大一点的值
        dp[newSpace] = max(dp[newSpace], dp[oldSpace] + w[i])

        ans = max(ans, dp[newSpace])

    # 最后返回整个数组中的最大值
    return ans

while True:
    try:
        line = raw_input()
    except:
        break
    ws = line.split()

    N = int(ws[0])
    V = int(ws[1])

    v = []
    w = []
    for i in range(N):
        line = raw_input()
        ws = line.split()
        vi, wi = ws
        v.append(int(vi))
        w.append(int(wi))

    s = Solution()
    print s.Solve(N, V, v, w)
