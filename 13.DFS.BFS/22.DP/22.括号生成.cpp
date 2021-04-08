/*
 * @lc app=leetcode.cn id=22 lang=cpp
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    vector<vector<string>> G{
        {""},
    };

   public:
    vector<string> generateParenthesis(int n) {
        if (n <= 0) {
            return {};
        }
        while (G.size() <= n) {
            const int s = G.size();
            G.emplace_back();
            auto &cur = G.back();
            for (int j = 0; j < s; j++) {
                auto &l = G[j];
                auto &r = G[s - j - 1];
                for (auto &u : l) {
                    for (auto &v : r) {
                        cur.push_back("(" + u + ")" + v);
                    }
                }
            }
        }

        return G[n];
    }
};
// @lc code=end
