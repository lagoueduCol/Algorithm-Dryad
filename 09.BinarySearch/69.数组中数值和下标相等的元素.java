// 测试平台: https://www.acwing.com/problem/content/65/
class Solution {
  private int getC(int[] A, int i) {
    final int v = A[i];
    if (v < i) {
      return -1;
    } else if (v == i) {
      return 0;
    }
    return 1;
  }

  public int getNumberSameAsIndex(int[] A) {
    if (A == null || A.length == 0) {
      return -1;
    }
    int l = 0, r = A.length;
    while (l < r) {
      final int m = l + ((r - l) >> 1);
      final int mov = getC(A, m);
      if (mov < 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    if (l < A.length && A[l] == l) {
      return l;
    }
    return -1;
  }
};
