/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    private List<String> first = new ArrayList<>();
    private List<List<String>> G = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0) {
            return ans;
        }

        if (G.size() == 0) {
            first.add("");
            G.add(first);
        }

        while (G.size() <= n) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < G.size(); j++) {
                List<String> L = G.get(j);
                List<String> R = G.get(G.size() - j - 1);
                for (String l: L) {
                    for (String r: R) {
                        cur.add("(" + l + ")" + r);
                    }
                }
            }
            G.add(cur);
        }

        return G.get(n);
    }
}
// @lc code=end

