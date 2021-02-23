// https://www.acwing.com/problem/content/description/788/
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
#include <set>
#include <stack>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
  int kth(int *A, int b, int e, int k) {
    if (b >= e) {
      return INT_MIN;
    }

    if (b + 1 == e) {
      return A[b];
    }

    const int x = A[b + ((e - b) >> 1)];
    int l = b;
    int i = b;
    int r = e - 1;

    while (i <= r) {
      if (A[i] < x)
        swap(A[l++], A[i++]);
      else if (A[i] == x)
        i++;
      else
        swap(A[r--], A[i]);
    }

    const int lcnt = l - b;
    const int mcnt = i - l;

    // check left side
    if (k < lcnt)
      return kth(A, b, l, k);
    // check right side
    if (k >= (lcnt + mcnt))
      return kth(A, i, e, k - lcnt - mcnt);
    return x;
  }

public:
  int KthNumber(int *A, int N, int k) { return kth(A, 0, N, k - 1); }
};

int N;
int K;
int A[100001];

int main(void) {
  Solution s;
  while (scanf("%d%d", &N, &K) != EOF) {
    for (int i = 0; i < N; i++) {
      scanf("%d", A + i);
    }
    printf("%d\n", s.KthNumber(A, N, K));
  }
  return 0;
}
