
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
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
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
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
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
import java.util.Queue;
import java.util.PriorityQueue;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        final int N = stations.length;
        int i = 0;

        // 当前汽车的状态{位置, 还余下多少汽油}
        int curPos = 0;
        int fuelLeft = startFuel;

        // 副油箱
        Queue<Integer> Q = new PriorityQueue<>((v1, v2) -> v2 - v1);

        // 从副油箱里面往汽车加油的次数
        int addFuelTimes = 0;

        while (curPos + fuelLeft < target) {
            // 默认期望的下一站，站点设置为target
            // 此时能加的汽油为0
            int pos = target;
            int fuel = 0;

            // 如果有位于target之前的站点, 那么更新
            // 加油站的位置，以及能加到副油箱的油量
            if (i < N && stations[i][0] <= target) {
                pos = stations[i][0];
                fuel = stations[i][1];
            }

            // 如果当前汽车的状态，不能到达期望的下一站
            while (curPos + fuelLeft < pos) {
                // 拿出副油箱啊
                // 惨了，副油箱没有油了，
                if (Q.isEmpty()) {
                    return -1;
                }
                // 从副油箱里面拿出最大的汽油加上去
                final int curMaxFuel = Q.peek();
                Q.poll();
                fuelLeft += curMaxFuel;
                // 加油次数++
                addFuelTimes++;
            }

            // 好了，现在可以到达期望的下一站了
            // 不过需要把消耗的汽油扣掉
            final int fuelCost = pos - curPos;

            // 更新当前汽车的状态
            fuelLeft -= fuelCost;
            curPos = pos;

            // 这个汽油站里面的汽油加到副油箱
            if (fuel > 0) {
                Q.offer(fuel);
            }

            // 这个站就过去了
            i++;
        }

        // 能到达target吗？如果能，返回加油次数，不能返回-1
        return curPos + fuelLeft >= target ? addFuelTimes : -1;
    }
}
