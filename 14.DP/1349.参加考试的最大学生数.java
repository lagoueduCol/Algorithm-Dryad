/*
 * @lc app=leetcode.cn id=1349 lang=java
 *
 * [1349] 参加考试的最大学生数
 *
 * https://leetcode-cn.com/problems/maximum-students-taking-exam/description/
 *
 * algorithms
 * Hard (48.76%)
 * Likes:    108
 * Dislikes: 0
 * Total Accepted:    2.6K
 * Total Submissions: 5.3K
 * Testcase Example:  '[["#",".","#","#",".","#"],[".","#","#","#","#","."],["#",".","#","#",".","#"]]'
 *
 * 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
 * 
 * 
 * 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的一起参加考试且无法作弊的最大学生人数。
 * 
 * 学生必须坐在状况良好的座位上。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：seats = [["#",".","#","#",".","#"],
 * [".","#","#","#","#","."],
 * ["#",".","#","#",".","#"]]
 * 输出：4
 * 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。 
 * 
 * 
 * 示例 2：
 * 
 * 输入：seats = [[".","#"],
 * ["#","#"],
 * ["#","."],
 * ["#","#"],
 * [".","#"]]
 * 输出：3
 * 解释：让所有学生坐在可用的座位上。
 * 
 * 
 * 示例 3：
 * 
 * 输入：seats = [["#",".",".",".","#"],
 * [".","#",".","#","."],
 * [".",".","#",".","."],
 * [".","#",".","#","."],
 * ["#",".",".",".","#"]]
 * 输出：10
 * 解释：让学生坐在第 1、3 和 5 列的可用座位上。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * seats 只包含字符 '.' 和'#'
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 * 
 * 
 */

// @lc code=start
class Solution {
    private boolean isValidSit(int j) {
        int pre_bit = 0;
        while (j != 0) {
            final int cur_bit = j & 0x01;
            if ((cur_bit == 1 && pre_bit == 1)) {
                return false;
            }
            j >>= 1;
            pre_bit = cur_bit;
        }
        return true;
    }

    private int sitMask(char[][] A, int r) {
        // 记录不能坐的情况
        int not_sit = 0;
        for (int c = 0; c < A[r].length; c++) {
            if (A[r][c] == '#') {
                not_sit |= 1 << c;
            }
        }
        return not_sit;
    }

    private int popCount(int x) {
        int ret = 0;
        while (x != 0) {
            ret += (x & 0x01) != 0 ? 1 : 0;
            x >>= 1;
        }
        return ret;
    }

    public int maxStudents(char[][] A) {
        if (A == null || A[0] == null ||
            A.length == 0 || A[0].length == 0) {
            return 0;
        }

        final int R = A.length;
        final int C = A[0].length;

        // 每一行的状态，只与上一行的状态有关，那么，我们在记录
        // 时候，只需要记录上一行的状态就可以了
        // 因此，只需要两行进行迭代

        int pre = 0;
        int cur = 1;

        // 首先处理第一行, 也就是把pre = 0给求出来
        // 如果只有一行的时候
        int[][] dp = new int[2][1 << C];

        // 处理第一行
        // 那么对于每一个有效的状态，我们都需要计算其相应的可以坐的人数
        // 注意状态的处理
        // 这里我们要记住第一行已经有多少个位置不能坐人了
        int pre_not_sit = sitMask(A, 0);

        // 备注：popCount 就是计算一个整数里面有多少个bit为1
        // GCC自带函数

        int pre_max_ans = 0;
        for (int i = 1; i < (1 << C); i++) {
            // 如果有人坐在了不能坐的位置上，那么这种情况直接跳过
            if (!((i & pre_not_sit) > 0 || !isValidSit(i))) {
                // 再看一下，有没有连续的1
                dp[pre][i] = popCount(i);
                pre_max_ans = Math.max(pre_max_ans, dp[pre][i]);
            }
        }

        for (int r = 1; r < R; r++) {
            // 这里开始处理第r行
            final int cur_not_sit = sitMask(A, r);

            dp[cur][0] = pre_max_ans;

            for (int i = 1; i < (1 << C); i++) {
                // 如果当前状态里面，与not_sit有重合的，这种情况直接收益为0
                if (((i & cur_not_sit)) != 0 || !isValidSit(i)) {
                    dp[cur][i] = 0;
                } else {
                    // 如果这种坐法是有效的
                    final int cnt = popCount(i);
                    dp[cur][i] = cnt;

                    // 前面可以坐的位置
                    int pre_status = (1 << C) - 1;
                    // 扣除掉前面不能坐的位置
                    for (int c = 0; c < C; c++) {
                        if (((1 << c) & i) != 0) {
                            // 扣除掉左上，右上
                            if (c - 1 >= 0) {
                                final int mask = 1 << (c - 1);
                                pre_status &= ~mask;
                            }

                            if (c + 1 < C) {
                                final int mask = 1 << (c + 1);
                                pre_status &= ~mask;
                            }
                        }
                    }

                    // 前面还有不能坐的位置
                    // 那么现在pre_status里面为1的情况，都是可以坐的地方
                    pre_status &= ~pre_not_sit;

                    // 那么我们需要看只有这几个位置可以坐的地方
                    for (int p = 0; p <= pre_status; p++) {
                        // 如果p里面的1，有不在pre_status里面的，那么不能处理
                        if ((p & (~pre_status)) != 0) {
                            continue;
                        }
                        dp[cur][i] = Math.max(dp[cur][i], dp[pre][p] + cnt);
                    }

                    pre_max_ans = Math.max(pre_max_ans, dp[cur][i]);
                }
            }

            // swap(cur, pre);
            int t = cur;
            cur = pre;
            pre = t;

            pre_not_sit = cur_not_sit;
        }

        int ans = 0;
        for (int i = 0; i < (1 << C); i++) {
            ans = Math.max(ans, dp[pre][i]);
        }
        return ans;
    }
}
// @lc code=end

