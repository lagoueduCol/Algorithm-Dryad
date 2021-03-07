int nonOverlapIntervals(vector<vector<int>> &A) {
  const int N = A.size();

  sort(A.begin(), A.end());

  int preStart = INT_MAX;
  int ans = 0;
  for (int i = N - 1; i >= 0; i--) {
    const int end = A[i][1];
    const int start = A[i][0];
    if (end <= preStart) {
      ans++;
      preStart = A[i][0];
    }
  }

  return ans;
}
