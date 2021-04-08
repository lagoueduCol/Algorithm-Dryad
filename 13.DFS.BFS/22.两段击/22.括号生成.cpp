/*
 * @lc app=leetcode.cn id=22 lang=cpp
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    struct node {
        string path;
        int lcnt = 0;
        int rcnt = 0;
    };

   public:
    vector<string> generateParenthesis(int n) {
        if (n <= 0) {
            return {};
        }

        vector<string> ans;

        vector<node> cur, tmp;

        node t;
        t.path = "(";
        t.lcnt = 1;

        cur.push_back(t);

        for (int i = 1; i < (n + n); i++) {
            tmp.clear();

            for (auto &c : cur) {
                if (c.lcnt >= c.rcnt && c.lcnt < n) {
                    node next = c;
                    next.path.push_back('(');
                    next.lcnt++;
                    tmp.push_back(next);
                }

                if (c.lcnt > c.rcnt) {
                    node next = c;
                    next.path.push_back(')');
                    next.rcnt++;
                    tmp.push_back(next);
                }
            }

            cur.swap(tmp);
        }

        for (auto &c : cur) {
            ans.push_back(c.path);
        }

        return ans;
    }
};
// @lc code=end
