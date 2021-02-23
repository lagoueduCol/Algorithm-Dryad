
#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

vector<int> findSmallSeq(vector<int> &nums, int k) {
  if (nums.empty()) {
    return {};
  }
  vector<int> ans(nums.size());
  stack<int> s;

  // 这里生成单调栈
  for (size_t i = 0; i < nums.size(); i++) {
    const int x = nums[i];
    const int left = nums.size() - i;
    // 注意我们想要提取出k个数，所以注意控制扔掉的数的个数
    while (!s.empty() && ((int)s.size() + left > k) && s.top() > x) {
      s.pop();
    }
    s.push(x);
  }

  // 如果递增栈里面的数太多，那么我们只需要取出前k个就可以了。
  // 多余的栈中的元素需要扔掉。
  while ((int)s.size() > k) {
    s.pop();
  }

  // 把k个元素取出来，注意这里取的顺序!
  for (int i = k - 1; i >= 0; i--) {
    ans[i] = s.top();
    s.pop();
  }

  return ans;
}

// 测试代码
void ASSERT_EQ(vector<int> A, vector<int> B, int k) {
  auto C = findSmallSeq(B, k);
  assert(A == C);
}

int main(void) {
  ASSERT_EQ({1, 2, 3}, {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 3);
  ASSERT_EQ({1, 2}, {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 2);
  ASSERT_EQ({1}, {9, 2, 4, 5, 1, 2, 6, 3, 100, 4}, 1);
  ASSERT_EQ({1}, {1, 1, 1, 1}, 1);
  ASSERT_EQ({1, 1}, {1, 1, 1, 1}, 2);
  ASSERT_EQ({1, 1, 1}, {1, 1, 1}, 3);
  return 0;
}