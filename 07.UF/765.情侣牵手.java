/*
 * @lc app=leetcode.cn id=765 lang=java
 *
 * [765] 情侣牵手
 *
 * https://leetcode-cn.com/problems/couples-holding-hands/description/
 *
 * algorithms
 * Hard (59.81%)
 * Likes:    238
 * Dislikes: 0
 * Total Accepted:    24.1K
 * Total Submissions: 36K
 * Testcase Example:  '[0,2,1,3]'
 *
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 一次交换可选择任意两人，让他们站起来交换座位。
 * 
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2,
 * 2N-1)。
 * 
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * 
 * 示例 1:
 * 
 * 
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 
 * 
 * 说明:
 * 
 * 
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 * 
 * 
 */

// @lc code=start


/**
 * 思路：
 *
 * 根据题意，[0, 1]需要连在一起。[2, 3]需要连在一起。
 *
 * 因此，直接把{0, 1}放在一个集合里面。{2, 3}放在一个集合里面。
 *
 * 这里面有两种情况：
 * Case A. [0, 1]本来就挨着的，那么在这时，{0, 1}本来就处在一个集合里面.
 *         [2, 3]如果本来也是挨着的，那么{2, 3}本来也就处在一个集合里面，
 *         此时也就不需要进行合并。因此，此时最少的合并次数为0
 *
 * Case B. [0, 1]没有挨在一起，而是打乱了，比如[0, 2, 1, 3]
 * 在这种情况一下，我们认为{0, 2, 1, 3}是一个集合. 这个集合是由
 *
 * [0, 1],  [2, 3]合并而来的，合并次数为1。
 * 那么如果要拆开，当然也需要经过一次交换。
 * 
 * 到这里，问题就变成了求解合并的次数。
 */

class Solution {
    private int[] F = null;
    private int unionCount = 0;

    private void Init(int n) {
        F = new int[n];
        for (int i = 0; i < n; i++) {
            // 注意这里在初始化的时候
            // [0, 1]需要处在一个集合里面
            // 无论他们在数组里面是不是相临
            F[i] = i - (i & 0x01);
        }
    }

    private int Find(int x) {
        if (x == F[x]) {
            return x;
        }
        F[x] = Find(F[x]);
        return F[x];
    }

    private void Union(int x, int y) {
        if (Find(x) != Find(y)) {
            unionCount++;
        }
        F[Find(x)] = Find(y);
    }

    public int minSwapsCouples(int[] A) {
        final int N = A == null ? 0 : A.length;

        Init(N);

        for (int i = 0; i < N; i += 2) {
            Union(A[i], A[i+1]);
        }

        return unionCount;
    }
}
// @lc code=end
