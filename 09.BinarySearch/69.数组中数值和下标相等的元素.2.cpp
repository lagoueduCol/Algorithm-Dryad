// https://www.acwing.com/problem/content/65/
class Solution {
  pair<int, int> getEqualRange(vector<int> &A) {
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
    // 寻找左边的起始点
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

    // 记录左边的起始点
    auto left = l;

    // 开始找右边的终点，形成区间
    // [left, right)
    // 注意开闭原则
    l = left, r = N;
    while (l < r) {
      const int m = l + ((r - l) >> 1);
      const int mov = getC(m);
      if (mov <= 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    // 记录右边的终点
    auto right = l;

    // 返回区间[left, right)
    // 在这个区间里面所有的数都与下标相等
    return {left, right};
  }

public:
  int getNumberSameAsIndex(vector<int> &A) {
    auto p = getEqualRange(A);

    // 这里我们已经找到的范围就是[p.first, p.second)
    // 由于题意只要求随意返回相等的一个数就是可以了
    // 所以这里我们就随便返回一个位置的数字就可以了
    if (p.first == p.second) {
      return -1;
    }
    return A[p.second - 1];
  }
};
