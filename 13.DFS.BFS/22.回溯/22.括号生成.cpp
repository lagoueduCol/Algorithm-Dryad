class Solution {
    void backtrace(int n, int idx, int left_cnt, int right_cnt, string &path,
                   vector<string> &ans) {
        if (idx == n + n) {
            if (left_cnt == right_cnt) {
                ans.push_back(path);
            }
        } else {
            if (left_cnt >= right_cnt && left_cnt < n) {
                path.push_back('(');
                backtrace(n, idx + 1, left_cnt + 1, right_cnt, path, ans);
                path.pop_back();
            }
            if (left_cnt > right_cnt) {
                path.push_back(')');
                backtrace(n, idx + 1, left_cnt, right_cnt + 1, path, ans);
                path.pop_back();
            }
        }
    }

   public:
    vector<string> generateParenthesis(int n) {
        if (n <= 0) {
            return {};
        }
        vector<string> ans;
        string path;
        backtrace(n, 0, 0, 0, path, ans);
        return ans;
    }
};
