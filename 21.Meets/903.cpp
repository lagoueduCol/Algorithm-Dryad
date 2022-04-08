// https://www.lintcode.com/problem/903/description

class Solution {
public:
    /**
     * @param length: the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    vector<int> getModifiedArray(int length, vector<vector<int>> &updates) {
        // Write your code here
        vector<int> A(length, 0/*value*/);

        for (auto &x: updates) {
            int startIndex = x[0];
            int endIndex = x[1];
            int value = x[2];

            if (startIndex > endIndex) {
                continue;
            }

            // 如果超出数组的范围
            if (startIndex >= length) {
                continue;
            }
            // 当startIndex小于0的时候，就从0开始
            startIndex = std::max(startIndex, 0);
            A[startIndex] += value;

            // 如果超出数组的范围
            if (endIndex < 0) {
                continue;
            }

            if (endIndex + 1 < length) {
                A[endIndex + 1] -= value;
            }
        }

        int pre = 0;
        for (int i = 0; i < A.size(); i++) {
            pre += A[i];
            A[i] = pre;
        }

        return A;
    }
};
