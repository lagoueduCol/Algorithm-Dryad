// 测试平台: https://www.acwing.com/problem/content/65/
class Solution {
public:
  int getNumberSameAsIndex(vector<int> &A) {
    const int N = A.size();

    auto getC = [&](const int i) {
      const int v = A[i];
      if (v < i) {
        return -1;
      } else if (v == i) {
        return 0;
      }
      return 1;
    };

    int l = 0, r = N;
    while (l < r) {
      const int m = l + ((r - l) >> 1);
      const int mov = getC(m);
      if (mov < 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }

    if (l < N && A[l] == l) {
      return l;
    }
    return -1;
  }
};
