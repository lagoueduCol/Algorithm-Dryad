/*
 * @lc app=leetcode.cn id=34 lang=cpp
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 */

// @lc code=start
class Solution {
  template <typename RandomIterator, typename ValueType>
  RandomIterator _lower_bound(RandomIterator first, RandomIterator last,
                              ValueType v) {
    if (first == last)
      return first;
    while (first != last) {
      const size_t d = distance(first, last);
      auto m = next(first, d >> 1);
      if (*m < v) {
        first = next(m, 1);
      } else {
        last = m;
      }
    }
    return first;
  }

  template <typename RandomIterator, typename ValueType>
  RandomIterator _upper_bound(RandomIterator first, RandomIterator last,
                              ValueType v) {
    if (first == last)
      return first;
    while (first != last) {
      auto d = distance(first, last);
      auto m = next(first, d >> 1);
      if (*m <= v) {
        first = m + 1;
      } else {
        last = m;
      }
    }
    return first;
  }

public:
  vector<int> searchRange(vector<int> &A, int T) {
    int l = _lower_bound(A.begin(), A.end(), T) - A.begin();
    if (l == A.size() || A[l] != T) {
      return {-1, -1};
    }
    int r = _upper_bound(A.begin() + l, A.end(), T) - A.begin();
    return {l, r - 1};
  }
};
// @lc code=end
