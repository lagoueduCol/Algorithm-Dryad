/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    private void backTrace(int n, int idx, int lcnt, int rcnt, StringBuilder path, List<String> ans) {
        if (idx == (n + n) && lcnt == rcnt) {
            ans.add(path.toString());
        } else {
            if (lcnt >= rcnt && lcnt < n) {
                path.append('(');
                backTrace(n, idx + 1, lcnt + 1, rcnt, path, ans);
                path.setLength(path.length() - 1);
            }

            if (lcnt > rcnt) {
                path.append(')');
                backTrace(n, idx + 1, lcnt, rcnt + 1, path, ans);
                path.setLength(path.length() - 1);
            }
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0) {
            return ans;
        }
        StringBuilder path = new StringBuilder();
        backTrace(n, 0, 0, 0, path, ans);
        return ans;
    }
}
// @lc code=end

