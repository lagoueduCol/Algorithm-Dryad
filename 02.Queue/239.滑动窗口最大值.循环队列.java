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
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
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

class Solution {
    class Queue {
        private int cap = 0;
        private int[] Q = null;

        private int head = 0;
        private int tail = 0;
        private int used = 0;

        public Queue(int capacity) {
            this.cap = capacity;
            Q = new int[cap];
        }

        private int backIndex() {
            return (tail - 1 + cap) % cap;
        }

        public void push(int val) {
            while (used > 0 && Q[backIndex()] < val) {
                used--;
                tail = backIndex();
            }
            Q[tail] = val;
            tail = (tail + 1) % cap;
            used++;
        }

        public int front() {
            return Q[head];
        }

        public void pop(int val) {
            if (used > 0 && Q[head] == val) {
                head = (head + 1) % cap;
                used--;
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[0];
        }

        Queue Q = new Queue(k + 1);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            Q.push(nums[i]);
            if (i < k - 1) {
                continue;
            }

            ans.add(Q.front());

            Q.pop(nums[i-k+1]);
        }

        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }
}

