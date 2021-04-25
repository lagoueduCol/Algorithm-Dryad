/*
 * @lc app=leetcode.cn id=743 lang=cpp
 *
 * [743] 网络延迟时间
 *
 * https://leetcode-cn.com/problems/network-delay-time/description/
 *
 * algorithms
 * Medium (46.62%)
 * Likes:    230
 * Dislikes: 0
 * Total Accepted:    22K
 * Total Submissions: 47K
 * Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
 *
 * 有 n 个网络节点，标记为 1 到 n。
 * 
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 * 
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * times[i].length == 3
 * 1 i, vi 
 * ui != vi
 * 0 i 
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 * 
 * 
 */

// @lc code=start
class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        // 初始化从k出发到其他各点的距离
        const uint64_t INF = INT_MAX;
        uint64_t dist[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = INF;
        }
        dist[k] = 0;

        // 最多更新 n + 1轮
        for (int updateTimes = 0; updateTimes <= n; updateTimes++) {
            for (auto &edge : times) {
                // 有向边 <from, to>
                const int from = edge[0], to = edge[1], cost = edge[2];
                dist[to] = min(dist[to], dist[from] + cost);
            }
        }

        // 找到数组中的最大值
        const uint64_t maxTime = *max_element(dist + 1, dist + n + 1);
        return maxTime >= INF ? -1 : maxTime;
    }
};
// @lc code=end

