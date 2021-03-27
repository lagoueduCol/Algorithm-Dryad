// https://leetcode-cn.com/problems/eight-queens-lcci/
class Solution {
    private boolean[] row = null;
    private boolean[] left = null;
    private boolean[] right = null;
    // 用来存放单次的解
    private char[][] A = null;

    // 用来存放所有的解
    private List<List<String>> ans = null;

    private void Init(int n) {
        row = new boolean[n];
        left = new boolean[n+n];
        right = new boolean[n+n];

        // 生成回溯所用的矩阵
        A = new char[n][n];
        for (int r = 0; r < n; r++) {
            Arrays.fill(A[r], '.');
        }

        // 存放所有的结果
        ans = new ArrayList<>();
    }

    private void Clone() {
        List<String> tmp = new ArrayList<>();
        for (int r = 0; r < A.length; r++) {
            tmp.add(new String(A[r]));
        }
        ans.add(tmp);
    }
    /**
     * c: start columns
     * C: total columns
     */
    private void dfs(int c, int C) {
        if (c >= C) {
            Clone();
            return;
        }

        // 当前这一列，一共有n行选择
        for (int r = 0; r < C; r++) {
            if (row[r] || left[r+c] || right[r + C -c]) {
                continue;
            }
            row[r] = left[r+c] = right[r+C-c] = true;
            A[r][c] = 'Q';
            dfs(c+1,C);
            A[r][c] = '.';
            row[r] = left[r+c] = right[r+C-c] = false;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        Init(n);
        dfs(0/*start_col*/, n/*total cols*/);
        return ans;
    }
}
