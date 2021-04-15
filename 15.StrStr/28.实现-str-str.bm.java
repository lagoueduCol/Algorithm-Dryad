/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (39.71%)
 * Likes:    773
 * Dislikes: 0
 * Total Accepted:    326.5K
 * Total Submissions: 822.2K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle
 * 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 *
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 *
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0
 * 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */

// @lc code=start
class Solution {
  static private int[] bad = new int[256];

  // suffix 后缀在sub字符串中的最右的起始位置：需要在其自身的左边。
  // prefix[i]数组则表示长度为i的后缀串是不是sub的前缀。
  static private int[] suffix = null;
  static private boolean[] prefix = null;

  /**
   * 记录每个字符在sub字符串中的出现的最右端的下标位置
   * 如果没有出现，那么设置为-1
   * 用于坏字符规则
   * @param sub
   */
  private void buildBadCharPos(String sub) {
    for (int j = 0; j < 256; j++) {
      bad[j] = -1;
    }

    for (int j = 0; j < sub.length(); j++) {
      bad[(int)sub.charAt(j)] = j;
    }
  }

  /**
   * 这个函数负责生成suffix和prefix
   * 这段代码需要仔细读注释
   * @param sub 要在main字符串中查找的字符串sub
   */
  private void buildSuffixPrefix(String sub) {
    int i = 0;
    int j = 0;
    int len = 0;
    final int n = sub.length();

    // 初始化
    // 设置所有的 prefix[] = false
    // 设置所有的 suffix[] = -1
    for (i = 0; i < n; i++) {
      prefix[i] = false;
      suffix[i] = -1;
    }

    for (i = 0; i < n - 1; i++) {
      j = i;
      len = 0;

      // 两个字符串：
      // 前缀字符串是P = sub[0...j]
      // 后缀字符串是S = t[(n-j-1)...n-1];
      // 当然，P和S是一样长的!
      // 比较顺序:
      // 在比较前缀字符串P和后缀字符串S的时候
      // 是从: `后面` 开始向前比较的
      // HINT:
      // 我们当然没有必要取出P和S
      // 在比较的时候，j--可以保证从后往前匹配
      // len++表示已经匹配的长度
      while (j >= 0 && sub.charAt(j) == sub.charAt(n - 1 - len)) {
        len++;
        // 这段代码非常有意思。
        // 我们要考虑以下场景才容易看懂：
        // 假设字符串sub = "ABABABAB";
        //
        // * i = 1:
        //      前缀字符串P = "AB" = sub[0,1];
        //      后缀字符串S = "AB" = sub[6,7];
        //   > j = 1:
        //     P[j=1] = 'B' == S[7] = 'B' 成立
        //     所以suffix[1=len('B')] = 1
        //     表示后缀串“B”在sub字符串左边的开始位置在1
        //   > j = 0:
        //     P[j=0] = 'A' == S[6] = 'A' 成立
        //     所以suffix[2=len('AB')] = 0
        //     表示后缀串"AB"在sub字符串左边的开始位置在0
        //
        // 接下来我们看当处理到i = 5的时候发生什么?
        //
        // * i = 5
        //      前缀字符串P = "ABABAB" = sub[0...5];
        //      后缀字符串S = "ABABAB" = sub[2...7];
        //   > j = 5
        //     P[j=5] = 'B' == Sub[7] = 'B' 成立
        //     所以suffix[1=len('B')] = j = 5
        //     表示后缀串“B”在sub字符串左边的开始位置在5
        //   > j = 4
        //     P[j=4] = 'A' == Sub[6] = 'A'成立
        //     所以suffix[2=len('AB')] = j = 4
        //     表示后缀串“AB”在sub字符串左边的开始位置在4
        // 到这里，我们发现
        // 通过这一行代码，我们可以找到每个后缀串在sub里面“最右边”的起始位置。
        // 注意：这里的最右边当然不能是后缀串本身，需要在后缀串的左边!
        suffix[len] = j;
        j--;
      }

      // 如果P字符串和S字符串完全一样
      // 那么说明，后缀字符串S能够匹配前缀
      // 这里要进行标记
      if (-1 == j) {
        prefix[len] = true;
      }
    }
  }

  /**
   * @param j
   *   sub字符串和main字符串从后往前匹配的时候，在sub[j]位置与main匹配失败
   *          也就是说: sub[j+1,...,n)都和main字符串匹配成功了，是一个好后缀
   * @param n sub字符串的长度
   * @return 依次使用a), b), c)返回相应的值
   */
  private int moveBySuffixPrefix(int j, int n) {
    // 因为已经匹配的位置是sub[j+1,n)
    // len表示已经匹配的字符串的长度
    int len = n - (j + 1);

    // 使用规则 a)
    if (suffix[len] != -1) {
      return j + 1 - suffix[len];
    }

    // 使用规则 b)
    // 这里也可以从r = j + 1开始。但是如果j+1是有效的。那么
    // 前面的suffix[len] != -1就会处理掉。
    // 所以这里没有必要再看j + 1
    // 直接找到一个可以匹配的后缀子串与前缀子串匹配的位置就可以了。
    // r表示在sub字符串中的下标，那么n - r就表示相应的后缀串
    for (int r = j + 2; r <= n - 1; r++) {
      if (prefix[n - r]) {
        return r;
      }
    }

    // 使用规则c)
    return n;
  }

  public int strStr(String main, String sub) {
    if (sub == null || sub.length() == 0) {
      return 0;
    }

    if (main == null || main.length() == 0) {
      return -1;
    }

    buildBadCharPos(sub);

    final int mainLen = main.length();
    final int subLen = sub.length();

    suffix = new int[subLen];
    prefix = new boolean[subLen];

    buildSuffixPrefix(sub);

    int i = 0;
    int j = 0;

    while (i <= mainLen - subLen) {
      for (j = subLen - 1; j >= 0; j--) {
        if (main.charAt(i + j) != sub.charAt(j)) {
          break;
        }
      }

      if (j < 0) {
        return i;
      }

      int badMoveLength = j - bad[(int)main.charAt(i + j)];
      int goodSuffixMoveLength = 0;

      // 有后缀串的时候，我们才去使用
      // 好后缀规则
      // 因为是在sub[j]匹配失败
      // 所以当j >= subLen - 1的时候
      // 是没有后缀串的!
      // 当然也没有好后缀串了
      if (j < subLen - 1) {
        goodSuffixMoveLength = moveBySuffixPrefix(j, subLen);
      }

      i += Math.max(badMoveLength, goodSuffixMoveLength);
    }

    return -1;
  }
}
// @lc code=end
