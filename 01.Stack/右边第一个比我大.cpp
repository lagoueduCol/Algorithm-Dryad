/*
 * 题目：给定一个数组，要找到这个数组里面每个元素右边比我大的元素的位置
 * - 注意：是右边第一个比我大的，如果有多个的话
 * - 如果没有，那么用-1表示。
 * 返回：一个数组，表示右边比我大的数的下标位置
 *
 * 输入：[5, 6]
 * 输出：[1, -1]
 * 解释：A[0] = 5，右边比我大的是A[1] = 6, 所以记录为 = 1
 *       A[1] = 6, 右边比我大的元素没有，所以记录为   = -1
 *       所以返回值是[1, -1]
 */

class Solution {

  vector<int> findRightLarge(vector<int> &A) {
    if (A.empty()) {
      return {};
    }
    // 结果数组
    vector<int> ans(A.size());

    // 注意，栈中的元素记录的是下标
    stack<int> t;

    for (size_t i = 0; i < A.size(); i++) {
      const int x = A[i];
      // 每个元素都向左遍历栈中的元素完成消除动作
      while (!t.empty() && A[t.top()] < x) {
        // 消除的时候，记录一下被谁消除了
        ans[t.top()] = i;
        // 消除时候，值更大的需要从栈中消失
        t.pop();
      }
      // 剩下的入栈
      t.push(i);
    }
    // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
    while (!t.empty()) {
      ans[t.top()] = -1;
      t.pop();
    }

    return ans;
  }
};
