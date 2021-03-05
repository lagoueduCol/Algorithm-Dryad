import java.util.Queue;

/*
 * @lc app=leetcode.cn id=1705 lang=java
 *
 * [1705] 吃苹果的最大数目
 *
 * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/description/
 *
 * algorithms
 * Medium (32.88%)
 * Likes:    33
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 12.9K
 * Testcase Example:  '[1,2,3,5,2]\n[3,2,1,4,2]'
 *
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i]
 * 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且
 * days[i] == 0 表示。
 * 
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 
 * 
 * 示例 2：
 * 
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 10^4
 * 0 <= apples[i], days[i] <= 2 * 10^4
 * 只有在 apples[i] = 0 时，days[i] = 0 才成立
 * 
 * 
 */

// @lc code=start
class Solution {
    class Node {
        // 记录果子数目
        public int num;
        // 记录哪天会坏掉
        public int bad;

        public Node(int a, int b) {
            num = a;
            bad = b;
        }
    }

    // A数组表示第i天要掉落的果子数
    // B表示从掉落那天起，i + B[i]那天立马坏掉不能吃了。
    public int eatenApples(int[] A, int[] B) {
        final int N = A == null ? 0 : A.length;

        // java小堆
        Queue<Node> Q = new PriorityQueue<>((a, b) -> {
            return a.bad - b.bad;
        });

        int ans = 0;
        int i = 1;

        while (i <= N || !Q.isEmpty()) {
            // 第i天得到 num 个苹果
            // 会在 bad 那天坏掉
            if (i <= N) {
                final int num = A[i - 1];
                final int bad = i + B[i - 1];
                if (num > 0) {
                    Q.offer(new Node(num, bad));
                }
            }

            // 把已经过期的都扔掉
            while (!Q.isEmpty() && (Q.peek().bad <= i)) {
                Q.poll();
            }

            if (!Q.isEmpty()) {
                // 选出今天吃的
                Node cur = Q.poll();
                ans++;
                if (--cur.num > 0) {
                    Q.offer(cur);
                }
            }
            i++;
        }

        return ans;
    }
}
// @lc code=end
