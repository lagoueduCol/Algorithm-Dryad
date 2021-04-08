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

        Queue<Node> Q = new LinkedList<>();

        Node cur = new Node();
        cur.path = "(";
        cur.lcnt = 1;
        cur.rcnt = 0;

        Q.offer(cur);

        while (Q.size() > 0) {
            final int oldSize = Q.size();

            for (int i = 0; i < oldSize; i++) {
                cur = Q.poll();
                if (cur.path.length() == (n + n) && cur.lcnt == cur.rcnt) {
                    ans.add(cur.path);
                    continue;
                }

                if (cur.lcnt >= cur.rcnt && cur.lcnt < n) {
                    Node next = new Node();
                    next.path = cur.path + "(";
                    next.lcnt = cur.lcnt + 1;
                    next.rcnt = cur.rcnt;
                    Q.offer(next);
                }

                if (cur.lcnt > cur.rcnt) {
                    Node next = new Node();
                    next.path = cur.path + ")";
                    next.lcnt = cur.lcnt;
                    next.rcnt = cur.rcnt + 1;
                    Q.offer(next);
                }
            }
        }

        return ans;
    }
}
// @lc code=end

