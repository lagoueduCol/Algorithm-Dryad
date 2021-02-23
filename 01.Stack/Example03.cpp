

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

vector<int> findRightSmall(vector<int> &A) {
  if (A.empty()) {
    return {};
  }
  // 结果数组
  vector<int> ans(A.size());

  // 注意，栈中的元素记录的是下标
  stack<int> t;

  for (size_t i = 0; i < A.size(); i++) {
    const int x = A[i];
    // 每个元素都向左遍历栈中的元素完成消除动作
    while (!t.empty() && A[t.top()] > x) {
      // 消除的时候，记录一下被谁消除了
      ans[t.top()] = i;
      // 消除时候，值更大的需要从栈中消失
      t.pop();
    }
    // 剩下的入栈
    t.push(i);
  }
  // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
  while (!t.empty()) {
    ans[t.top()] = -1;
    t.pop();
  }

  return ans;
}

// 测试代码
void ASSERT_EQ(vector<int> A, vector<int> B) {
  auto C = findRightSmall(B);
  assert(A == C);
}

int main(void) {
  ASSERT_EQ({1, -1}, {5, 2});
  ASSERT_EQ({5, 5, 5, 4, 5, -1, -1}, {1, 2, 4, 9, 4, 0, 5});
  return 0;
}