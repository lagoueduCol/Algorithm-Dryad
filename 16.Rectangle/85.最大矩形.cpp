/*
 * @lc app=leetcode.cn id=85 lang=cpp
 *
 * [85] 最大矩形
 *
 * https://leetcode-cn.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (51.62%)
 * Likes:    884
 * Dislikes: 0
 * Total Accepted:    75.8K
 * Total Submissions: 146.7K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：matrix =
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：matrix = []
 * 输出：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：matrix = [["0"]]
 * 输出：0
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：matrix = [["1"]]
 * 输出：1
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 
 * matrix[i][j] 为 '0' 或 '1'
 * 
 * 
 */

// @lc code=start

class Solution {
    int64_t compute(vector<int>& a) {
        const int n = a.size();
        stack<int> s;
        int64_t ans = 0;
        for (int i = 0; i < n; i++) {
            while (!s.empty() && a[i] <= a[s.top()]) {
                const int64_t h = a[s.top()];
                s.pop();
                const int64_t base = s.empty() ? -1 : s.top();
                const int64_t area = h * (i - base - 1);
                ans = max(ans, area);
            }
            s.push(i);
        }
        return ans;
    }

   public:
    int maximalRectangle(vector<vector<char>>& a) {
        if (!a.size() || !a[0].size()) return 0;
        const int rows = a.size();
        const int cols = a[0].size();
        vector<int> sum(cols + 1, 0);
        int64_t ans = 0;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; c++)
                sum[c] = a[r][c] == '1' ? sum[c] + 1 : 0;
            ans = max(ans, compute(sum));
        }
        return ans;
    }
};

// @lc code=end

