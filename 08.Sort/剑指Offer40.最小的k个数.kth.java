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
    private int kth(int[] a, int b, int e, int k) {
        if (b >=e ) {
            return 0;
        }

        // 递归结束，这个时候已经只有一个元素了
        if ((b + 1) >= e) {
            return a[b];
        }

        final int m = b + ((e-b) >> 1);
        final int x = a[m];

        // 这里会有四个区间
        // [b, l)[l, i)[i, r](r..N)
        // [b, l) 是小于x的数所在区间
        // [l, i) 是等于x的数所在区间
        // [i..r]是未处理的数据所在的区间
        // (r...N)是大于x的数据的区间
        int l = b, i = b, r = e - 1;
        while (i <= r) {
            if (a[i] < x) {
                // swap(a[l], a[i]);
                int t = a[l];
                a[l] = a[i];
                a[i] = t;
                i++;
                l++;
            } else if (a[i] == x) {
                i++;
            } else {
                // swap(a[r--], a[i]);
                int t = a[r];
                a[r] = a[i];
                a[i] = t;
                r--;
            }
        }

        // 由于数据已经被分成了三个部分。那么需要根据这三个部分来判断
        // kth在哪个部分里面

        // 如果kth在前面的那一部分
        if (l - b >= k) {
            return kth(a, b, l, k);
        }

        // 如果kth在中间等于x的那一部分
        if (i - b >= k) {
            return a[l];
        }

        // 如果kth在大于x的那一部分
        return kth(a, i, e, k - (i - b));
    }
    public int[] getLeastNumbers(int[] arr, int k) {
        final int kthValue = kth(arr, 0, arr.length, k);
        int kthValueCnt = 0;

        int[] ans = new int[k];
        int idx = 0;
        // 首先将小于kth的那部分数放到ans里面。
        for (int x: arr) {
            if (x < kthValue) {
                ans[idx++] = x;
            }
            if (x == kthValue) {
                kthValueCnt++;
            }
        }

        // 如果不足，那么并且有多余的，等于kthValue的元素
        // append它
        // 比如[1, 2, 2, 2, 2, 2, 2, 2, 2] k = 2
        // 前面的处理，只会把[1]放到ans里面。
        // 很明显，我们还需要把kthValue=2放到ans里面。
        while (idx < k && kthValueCnt > 0) {
            ans[idx++] = kthValue;
            kthValueCnt--;
        }

        return ans;
    }
}
