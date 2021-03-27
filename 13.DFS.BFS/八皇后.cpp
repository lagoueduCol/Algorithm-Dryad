// 八皇后 https://leetcode-cn.com/problems/eight-queens-lcci/
class Solution {
    int N = 0;
    vector<bool> row;
    vector<bool> left;
    vector<bool> right;
    vector<string> A;
    vector<vector<string>> ans;
    void dfs(int c) {
        // 如果每一列都填满了
        if (c == N) {
            ans.push_back(A);
            return;
        }

        // 每一列有N行的选择
        for (int r = 0; r < N; r++) {
            // 如果会被攻击
            if (row[r] || left[r+c] || right[r+N-c]) {
                continue;
            }

            A[r][c] = 'Q';
            row[r] = true;
            left[r+c] = true;
            right[r+N-c] = true;
            dfs(c + 1);
            A[r][c] = '.';
            row[r] = false;
            left[r+c] = false;
            right[r+N-c] = false;
        }
    }
public:
    vector<vector<string>> &solveNQueens(int n) {
        // 将结果矩阵清空
        ans.clear();
        // 记住正方形
        N = n;
        A.clear();
        A.resize(N, string(N, '.'));

        // 对角线的计算方式
        // A: i + j
        // 如果是 \ 方向上的对角线
        // B: i + C - j
        row.resize(N, false);
        left.resize(N + N, false);
        right.resize(N + N, false);

        // 求解
        dfs(0);

        return ans;
    }
};
