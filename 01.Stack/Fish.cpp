// 测试链接：https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/start/
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

int solution(vector<int> &A, vector<int> &B) {
  // write your code in C++14 (g++ 6.2.0)
  const int N = A.size();
  if (N <= 1)
    return N;
  constexpr int L = 0;
  constexpr int R = 1;

  stack<int> t;
  for (int i = 0; i < N; i++) {
    const int D = B[i];
    const int S = A[i];

    bool has_eat = false;
    while (!t.empty() && B[t.top()] == R && D == L) {
      // 如果栈顶的鱼比较大，那么把新来的吃掉
      if (A[t.top()] > S) {
        has_eat = true;
        break;
      }
      t.pop();
    }
    if (has_eat)
      continue;

    t.push(i);
  }

  return t.size();
}