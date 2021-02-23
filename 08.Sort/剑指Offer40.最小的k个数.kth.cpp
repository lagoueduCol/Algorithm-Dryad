/**
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
示例 2：

输入：arr = [0,1,2,1], k = 1
输出：[0]
 

限制：

0 <= k <= arr.length <= 10000
0 <= arr[i] <= 10000

*/
// 测试平台链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/submissions/
class Solution {
    // 这里采用类似快排的思想来找一个数组中第k小的元素
    // 这里采用的是三路快排的思路
    int kth(vector<int> &A, int b, int e, int k) {

        if (b >= e) {
            return 0;
        }
        if (b + 1 == e) {
            return A[b];
        }

        // 首先找到一个数，将这个数组分成三个部分
        int m = b + ((e-b)>>1);
        const int x = A[m];

        // 这里会有四个区间
        // [b, l)[l, i)[i, r](r..N)
        // [b, l) 是小于x的数所在区间
        // [l, i) 是等于x的数所在区间
        // [i..r]是未处理的数据所在的区间
        // (r...N)是大于x的数据的区间
        int l = b, i = b, r = e - 1;
        while (i <= r) {
            if (A[i] < x) {
                swap(A[l++], A[i++]);
            } else if (A[i] == x) {
                i++;
            } else {
                swap(A[r--], A[i]);
            }
        }

        // 由于数据已经被分成了三个部分。那么需要根据这三个部分来判断
        // kth在哪个部分里面

        // 如果kth在前面的那一部分
        if (l - b >= k) {
            return kth(A, b, l, k);
        }

        // 如果kth在中间等于x的那一部分
        if (i - b >= k) {
            return A[l];
        }

        // 如果kth在大于x的那一部分
        return kth(A, i, e, k - (i - b));
    }
public:
    vector<int> getLeastNumbers(vector<int>& A, int k) {
        const int kthValue = kth(A, 0, A.size(), k);
        int kthValueCnt = 0;

        vector<int> ans;
        // 首先将小于kth的那部分数放到ans里面。
        for (auto x: A) {
            if (x < kthValue) {
                ans.push_back(x);
            }
            kthValueCnt += x == kthValue;
        }

        // 如果不足，那么并且有多余的，等于kthValue的元素
        // append它
        // 比如[1, 2, 2, 2, 2, 2, 2, 2, 2] k = 2
        // 前面的处理，只会把[1]放到ans里面。
        // 很明显，我们还需要把kthValue=2放到ans里面。
        while (ans.size() < k && kthValueCnt > 0) {
            ans.push_back(kthValue);
            kthValueCnt--;
        }

        return ans;
    }
};
