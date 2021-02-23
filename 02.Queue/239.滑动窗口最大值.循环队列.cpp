/*
 *
 * [239] 滑动窗口最大值
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (49.00%)
 * Likes:    670
 * Dislikes: 0
 * Total Accepted:    93K
 * Total Submissions: 189.7K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * 给定一个数组
 * nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的
 * k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 * ⁠ 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 *
 */

#include <vector>

using namespace std;


class Queue {
   public:
    Queue(int mod) : q(mod) {}

    int backIndex() { return (tail - 1 + q.size()) % q.size(); }

    void push(int val) {
        while (used > 0 && q[backIndex()] < val) {
            tail = backIndex();
            used--;
        }
        q[tail] = val;
        used++;
        tail = (tail + 1) % q.size();
    }

    void pop(int val) {
        if (used > 0 && q[head] == val) {
            head = (head + 1) % q.size();
            used--;
        }
    }

    int front() { return q[head]; }

   private:
    // 循环队列的存储空间
    vector<int> q;
    // 用三个变量来实现循环队列
    int head = 0;
    int tail = 0;
    int used = 0;
};

class Solution {
   public:
    vector<int> maxSlidingWindow(vector<int> &nums, int k) {
        if (k > nums.size() || k <= 0) {
            return {};
        }

        Queue Q(k);

        vector<int> ans;
        for (int i = 0; i < nums.size(); i++) {
            Q.push(nums[i]);
            if (i < k - 1) {
                continue;
            }

            ans.push_back(Q.front());

            Q.pop(nums[i - k + 1]);
        }

        return ans;
    }
};
