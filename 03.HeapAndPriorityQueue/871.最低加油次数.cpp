/*
 *
 * [871] 最低加油次数
 *
 * https://leetcode-cn.com/problems/minimum-number-of-refueling-stops/description/
 *
 * algorithms
 * Hard (31.35%)
 * Likes:    104
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 16.7K
 * Testcase Example:  '1\n1\n[]'
 *
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 *
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1]
 * 升汽油。
 *
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1
 * 英里就会用掉 1 升汽油。
 *
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 *
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1
 * 。
 *
 * 注意：如果汽车到达加油站时剩余燃料为
 * 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为
 * 0，仍然认为它已经到达目的地。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 *
 *
 * 示例 2：
 *
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 *
 *
 * 示例 3：
 *
 * 输入：target = 100, startFuel = 10, stations =
 * [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60
 * 升。 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] <
 * target
 *
 *
 */

#include <assert.h>
#include <limits.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <algorithm>
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right)
        : val(x), left(left), right(right) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
   public:
    int minRefuelStops(int target, int startFuel,
                       vector<vector<int>> &stations) {
        const int N = stations.size();

        // 出发地点是从0位置开始出发
        int curPos = 0;

        // 用来存放汽油的队列
        priority_queue<int> Q;

        // 还余下多少汽油
        int gasLeft = startFuel;

        // 将要到达哪个加油站了
        int i = 0;
        // 加油的次数
        int addGasTimes = 0;

        while (curPos + gasLeft < target) {
            // 默认把target设置为最后一个站
            int curStationPos = target;
            int curStationGas = 0;

            // 如果在target前面还有站，那么需要设置这个站的位置
            // 以及油量
            if (i < N && stations[i][0] <= target) {
                curStationPos = stations[i][0];
                curStationGas = stations[i][1];
            }

            // 如果当前余下的汽油还不足以跑到这个加油站
            while (curPos + gasLeft < curStationPos) {
                if (Q.empty()) {
                    return -1;
                }
                // 那么找出以前加油最多的油来加
                // 把最多的油加上去
                gasLeft += Q.top();
                Q.pop();
                addGasTimes++;
            }

            // 向前开的过程中，肯定存在油量的消耗
            const int gasCost = curStationPos - curPos;
            gasLeft -= gasCost;

            // 把这个加油站的油放到队列中
            // 但是并不代表加到车里面了，只是作为备用油存到队列里面。
            if (curStationGas > 0)
                Q.push(curStationGas);

            // 过了这个站
            i++;
            // 更新当前车辆的位置
            curPos = curStationPos;
        }

        return curPos + gasLeft >= target ? addGasTimes : -1;
    }
};

int main(void) {
    Solution s;
    vector<vector<int>> A{{10, 60}, {20, 30}, {30, 30}, {60, 40}};
    std::cout << s.minRefuelStops(100, 10, A) << std::endl;
    return 0;
}
