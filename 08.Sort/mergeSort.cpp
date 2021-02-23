class Solution {
  void msort(vector<int> &a, int b, int e, vector<int> &t) {
    if (b >= e || b + 1 >= e) {
      return;
    }

    const int m = b + ((e - b) >> 1);

    msort(a, b, m, t);
    msort(a, m, e, t);

    int i = b;
    int j = m;
    int to = b;

    while (i < m || j < e) {
      if (j >= e || i < m && a[i] <= a[j]) {
        t[to++] = a[i++];
      } else {
        t[to++] = a[j++];
      }
    }

    for (int i = b; i < e; i++) {
      a[i] = t[i];
    }
  }

public:
  void mergeSort(vector<int> &nums) {
    vector<int> t(nums.size());
    msort(nums, 0, nums.size(), t);
  }
};
