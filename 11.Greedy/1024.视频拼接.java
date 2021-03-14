/*
 * @lc app=leetcode.cn id=1024 lang=java
 *
 * [1024] 视频拼接
 *
 * https://leetcode-cn.com/problems/video-stitching/description/
 *
 * algorithms
 * Medium (56.88%)
 * Likes:    223
 * Dislikes: 0
 * Total Accepted:    29.9K
 * Total Submissions: 52.6K
 * Testcase Example:  '[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]\n10'
 *
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1]
 * 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1
 * 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：clips =
 * [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]],
 * T = 9
 * 输出：3
 * 解释： 
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 0 
 * 
 * 
 */

// @lc code=start


// @lc code=start
class Solution {
    public int videoStitching(int[][] A, int T) {
        final int N = A == null ? 0 : A.length;
        if (T <= 0) {
            return 0;
        }

        if (N <= 0) {
            return -1;
        }

        Arrays.sort(A, new Comparator<int[]>() {
            private int cmp(int a, int b) {
                if (a == b) return 0;
                return a < b ? -1 : 1;
            }

            public int compare(int[] a, int[] b) {
                // 这里是根据一个区间的结束来进行排序
                if (a[0] != b[0]) return cmp(a[0], b[0]);
                return cmp(a[1], b[1]);
            }
        });

        // 我们需要覆盖的洞是[0, T]
        int i = 0;
        // 需要的切片数
        int ans = 0;
        int end = 0;

        while (i < N && end < T) {
            final int old = end;
            int pos = end;
            int j = i;
            // 后面的开头，一定要start=A[j][0] <= 当前的结尾
            while (j < N && A[j][0] <= old) {
                if (A[j][1] > pos) {
                    i = j;
                    pos = A[j][1];
                }
                j++;
            }

            // 不能再前进了
            if (pos == old) {
                return -1;
            }

            // 看一下选出来的是否合格
            if (pos <= 0) {
                // 继续往前走
                continue;
            }

            end = A[i][1];

            // 此时pos > 0
            // 那么这个切片要
            ans++;
        }

        return ans;
    }
}
// @lc code=end



// @lc code=end

