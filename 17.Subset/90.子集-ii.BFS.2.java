/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (61.92%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    71.4K
 * Total Submissions: 115.3K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */

// @lc code=start
class Counter extends HashMap<Integer, Integer> {
    public int get(Integer k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(int k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Counter cnt = new Counter();
        for (int x: nums) {
            cnt.add(x, 1);
        }

        List<List<Integer>> cur = new ArrayList<>();
        cur.add(new ArrayList<>());

        for (Map.Entry<Integer,Integer> en: cnt.entrySet()) {

            // BFS的next
            List<List<Integer>> next = new ArrayList<>();

            for (int addTimes = 1; addTimes <= en.getValue(); addTimes++) {
                // 遍历cur中的每一个subset
                for (List<Integer> subset: cur) {

                    // 生成新的subset
                    List<Integer> newSubset = new ArrayList<>(subset);
                    // 添加选中的数 addTimes次到集合中
                    for (int j = 0; j < addTimes; j++) {
                        newSubset.add(en.getKey());
                    }

                    next.add(newSubset);
                }
            }

            // 将next放到cur中。
            for (List<Integer> subset: next) {
                cur.add(subset);
            }
        }
        return cur;
    }
}
// @lc code=end

