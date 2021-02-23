// 测试平台: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        if (k <= 0 || arr.empty()) {
            return {};
        }

        if (arr.size() <= k) {
            return arr;
        }

        // c++默认是大堆
        priority_queue<int> Q;

        for (auto x: arr) {
            // 所有元素都要入堆
            Q.push(x);
            // 如果超出k个元素
            // 那么将较大的元素扔掉
            while (Q.size() > k) {
                Q.pop();
            }
        }

        // Q中已经保留了最小的k个数，这里生成返回值
        vector<int> ans;
        while (Q.size() > 0) {
            ans.push_back(Q.top());
            Q.pop();
        }

        return ans;
    }
};
