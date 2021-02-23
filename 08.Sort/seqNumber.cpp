class Solution {
  void msort(vector<int> &a, int b, int e, vector<int> &t, int &cnt) {
    if (b >= e || b + 1 >= e) {
      return;
    }
    const int m = b + ((e - b) >> 1);
    msort(a, b, m, t, cnt);
    msort(a, m, e, t, cnt);

    int i = b;
    int j = m;
    int to = b;

    while (i < m || j < e) {
      if (j >= e || i < m && a[i] <= a[j]) {
        t[to++] = a[i++];
      } else {
        t[to++] = a[j++];
        cnt += i - b;
      }
    }

    for (int i = b; i < e; i++) {
      a[i] = t[i];
    }
  }

public:
  int reversePairs(vector<int> &nums) {
    int cnt = 0;
    vector<int> t(nums.size());
    msort(nums, 0, nums.size(), t, cnt);
    return cnt;
  }
};
