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

import java.util.*;

// @lc code=start
class Solution {
    // 将两个合并成一个
    private List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> tmp = new ArrayList<>(a);

        for (Integer x: b) {
            tmp.add(x);
        }

        return tmp;
    }

    // 查看这段区域里面的值是不是都是一样的
    private boolean isSame(int[] nums, int b, int e) {
        boolean same = true;

        for (int i = b; i < e; i++) {
            if (nums[i] != nums[b]) {
                return false;
            }
        }

        return true;
    }

    // 注意：这里给的区间是左闭右开[b, e)
    private List<List<Integer>> dq(int[] nums, int b, int e) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        if (b >= e) {
            return ans;
        }

        // 如果整段区间里面的值都是一样的
        // 那么在选择的时候，只有e - b种情况
        // 比如有[2, 2]
        // 那么这里会添加子集
        // - [2]
        // - [2, 2]
        // 使用原则1：
        boolean same =isSame(nums, b, e);
        if (same) {
            List<Integer> tmp = new ArrayList<>();

            for (int i = b; i < e; i++) {
                tmp.add(nums[i]);
                ans.add(new ArrayList<>(tmp));
            }

            return ans;
        }

        // 如果数组里面没有重复元素，那么只需要从中间切分开就可以了
        int mid = b + ((e-b)>>1);

        // 接下来是使用原则2：要让所有相同的数，必须要当成一个整体
        // 不能被切散了
        int l = mid - 1;
        while (l >= b && nums[l] == nums[mid]) {
            l--;
        }

        int r = mid;
        while (r < e && nums[r] == nums[mid]) {
            r++;
        }

        // 正常情况下，我们切分点都选择 r
        int cutPos = r;

        // 但是如果右边[r, e)这个区间已经没有数了
        if (r >= e) {
            // 我们需要把(l, r)这个区间划给右边
            cutPos = l + 1;
        }

        List<List<Integer>> lans = dq(nums, b, cutPos);
        List<List<Integer>> rans = dq(nums, cutPos, e);

        // 两两组合
        for (List<Integer> x: lans) {
            for (List<Integer> y: rans) {
                // 由于我们已经在ans中存放了空集，所以如果遇到两个都是空集的时候
                // 我们就不再进行merge
                if (x.isEmpty() && y.isEmpty()) {
                    continue;
                }
                ans.add(merge(x, y));
            }
        }

        return ans;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        return dq(nums, 0, nums.length);
    }
}
// @lc code=end

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,2,3};
        List<List<Integer>> ans = s.subsetsWithDup(nums);
        for (List<Integer> l : ans) {
            System.out.print("{");
            for (Integer x: l) {
                System.out.print(x + ", ");
            }
            System.out.println("}");
        }
    }
}