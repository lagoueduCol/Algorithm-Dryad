/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    class Node {
        public String path;
        public int lcnt = 0;
        public int rcnt = 0;
        public Node() {}
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0) {
            return ans;
        }

        List<Node> cur = new ArrayList<>();
        Node x = new Node();
        x.path = "(";
        x.lcnt = 1;
        x.rcnt = 0;
        cur.add(x);

        while (cur.size() > 0) {
            List<Node> tmp = new ArrayList<>();
            for (Node c: cur) {
                if (c.path.length() == (n+n) && c.lcnt == c.rcnt) {
                    ans.add(c.path);
                    continue;
                }

                if (c.lcnt >= c.rcnt && c.lcnt < n) {
                    Node next = new Node();
                    next.path = c.path + "(";
                    next.lcnt = c.lcnt + 1;
                    next.rcnt = c.rcnt;
                    tmp.add(next);
                }

                if (c.lcnt > c.rcnt) {
                    Node next = new Node();
                    next.path = c.path + ")";
                    next.lcnt = c.lcnt;
                    next.rcnt = c.rcnt + 1;
                    tmp.add(next);
                }
            }

            cur = tmp;
        }
        return ans;
    }
}
// @lc code=end

