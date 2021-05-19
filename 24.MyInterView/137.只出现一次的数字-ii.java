/*
 * @lc app=leetcode.cn id=137 lang=java
 *
 * [137] 只出现一次的数字 II
 *
 * https://leetcode-cn.com/problems/single-number-ii/description/
 *
 * algorithms
 * Medium (68.50%)
 * Likes:    498
 * Dislikes: 0
 * Total Accepted:    49.5K
 * Total Submissions: 72.3K
 * Testcase Example:  '[2,2,3,2]'
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 
 * 说明：
 * 
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 
 * 示例 1:
 * 
 * 输入: [2,2,3,2]
 * 输出: 3
 * 
 * 
 * 示例 2:
 * 
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * 
 */

// @lc code=start
class Solution {
    public int singleNumber(int[] nums) {
        int one = 0;
        int two = 0;

        for (int x: nums) {
            // 产生进位
            int carry = one & x;
            one ^= x;

            // 计数为2的情况与进位相加
            // 如果我们只看某个bit位：
            // - 由于我们bit计数的状态只有
            //   0, 1, 2
            // - 当新来一个bit的时候，
            //   最大的计算结果是3
            // => 因此，不可能同时two与carry在某bit
            //    都是1的情况
            two ^= carry;

            // 当one与two的某个bit位都是1
            // 表示计数出现了3
            // 我们需要把这个3减掉。
            int cnt = one & two;
            one &= ~cnt;
            two &= ~cnt;
        }

        return one;
    }
}
// @lc code=end

