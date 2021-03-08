int nonOverlapIntervals(vector<vector<int>> &A) {
  const int N = A.size();

  sort(A.begin(), A.end(), [](const vector<int> &a, const vector<int> &b) {
    return a[1] != b[1] ? a[1] < b[1] : a[0] < b[0];
  });

  int preEnd = INT_MIN;
  int ans = 0;
  for (int i = 0; i < N; i++) {
    const int start = A[i][0];
    const int end = A[i][1];
    if (preEnd <= start) {
      ans++;
      preEnd = end;
    }
  }

  return ans;
}
