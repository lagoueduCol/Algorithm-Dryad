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
  
  private int getLeftSide(int[] A) {
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
    return l;
  }
  
  private int getRightSide(int[] A, int start) {
    int l = start, r = A.length;
    while (l < r) {
      final int m = l + ((r - l) >> 1);
      final int mov = getC(A, m);
      if (mov <= 0) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }

  public int getNumberSameAsIndex(int[] A) {
    if (A == null || A.length == 0) {
      return -1;
    }
    final int l = getLeftSide(A);
    final int r = getRightSide(A, l);

    // 这里我们已经找到的范围就是[l, r)
    // 由于题意只要求随意返回相等的一个数就是可以了
    // 所以这里我们就随便返回一个位置的数字就可以了
    return l == r ? -1 : A[r-1];
  }
};
