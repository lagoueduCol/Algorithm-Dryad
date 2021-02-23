// https://nanti.jisuanke.com/t/T1560
// 出错点：注意一定要使用long long类型，或者int64_t类型
// 出错点：给定的数组不是有序的
#include <algorithm>
#include <stdint.h>
#include <stdio.h>

int64_t A[1000080];

bool binary_search(int64_t *A, int n, int64_t target) {
  int l = 0, r = n;
  while (l < r) {
    const int m = l + ((r - l) >> 1);
    if (A[m] == target) {
      return true;
    }
    if (A[m] < target) {
      l = m + 1;
    } else {
      r = m;
    }
  }
  return false;
}

int main(void) {
  int n, m;
  int64_t x;
  while (scanf("%d%d", &n, &m) != EOF) {
    for (int i = 0; i < n; i++)
      scanf("%lld", A + i);
    std::sort(A, A + n);
    for (int i = 0; i < m; i++) {
      scanf("%lld", &x);
      printf("%s\n", binary_search(A, n, x) ? "YES" : "NO");
    }
  }
  return 0;
}
