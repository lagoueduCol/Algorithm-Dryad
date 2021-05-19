/*
 * @lc app=leetcode.cn id=679 lang=java
 *
 * [679] 24 点游戏
 *
 * https://leetcode-cn.com/problems/24-game/description/
 *
 * algorithms
 * Hard (54.27%)
 * Likes:    295
 * Dislikes: 0
 * Total Accepted:    23.4K
 * Total Submissions: 43.2K
 * Testcase Example:  '[4,1,8,7]'
 *
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * 
 * 示例 1:
 * 
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 
 * 
 * 示例 2:
 * 
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 
 * 
 * 注意:
 * 
 * 
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1
 * 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 将cards中的cards[i], cards[j] 经过某种运算之后 生成了value
    // 然后生成一个新的数组
    private double[] getNextCards(double[] cards, int i, int j, double v) {
        final int N = cards.length;
        double[] ans = new double[N-1];

        int to = 0;
        for (int k = 0; k < N; k++) {
            if (k != i && k != j) {
                ans[to++] = cards[k];
            }
        }
        ans[to++] = v;

        return ans;
    }

    // 判断是否达到了答案，注意浮点数的判断
    private boolean isResult(double value) {
        if (Math.abs(value - 24.0) < 1e-6) {
            return true;
        }
        return false;
    }

    private boolean notZero(double value) {
        return Math.abs(value) > 1e-6;
    }

    private boolean judge(double[] cards) {
        if (cards == null) {
            return false;
        }

        final int N = cards.length;

        // 如果已经只有一个数了，那么检查一下看看是否
        // 是24
        if (N == 1) {
            return isResult(cards[0]);
        }

        // 否则我们挑两个数，进行加减乘除
        // 其中加法和乘法没有交换的必要
        // 所以我们只需要check两个就可以了
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (judge(getNextCards(cards, i, j, cards[i] + cards[j])) || /* 加法 */
                    judge(getNextCards(cards, i, j, cards[i] * cards[j])) || /* 乘法 */
                    notZero(cards[j]) &&
                        judge(getNextCards(cards, i, j, cards[i] / cards[j])) || /* 除法 */
                    notZero(cards[i]) &&
                        judge(getNextCards(cards, i, j, cards[j] / cards[i])) || /* 除法 */
                    judge(getNextCards(cards, i, j, cards[i] - cards[j])) || /* 减法 */
                    judge(getNextCards(cards, i, j, cards[j] - cards[i])) /* 减法 */
                ) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean judgePoint24(int[] cards) {
        if (cards == null) {
            return false;
        }

        double[] dCards = new double[cards.length];
        int to = 0;
        for (int x: cards) {
            dCards[to++] = x;
        }

        return judge(dCards);
    }
}
// @lc code=end


// 将cards中的cards[i], cards[j] 经过某种运算之后 生成了value
// 然后生成一个新的数组
double[] getNextCards(double[] cards, int i, int j, double v) {
  final int N = cards.length;
  double[] ans = new double[N - 1];

  int to = 0;
  for (int k = 0; k < N; k++) {
    if (k != i && k != j) {
      ans[to++] = cards[k];
    }
  }
  ans[to++] = v;

  return ans;
}

// 判断是否达到了答案，注意浮点数的判断
boolean isResult(double value) {
  if (Math.abs(value - 24.0) < 1e-6) {
    return true;
  }
  return false;
}

boolean notZero(double value) {
  return Math.abs(value) > 1e-6;
}

boolean
judge(double[] cards) {
  if (cards == null) {
    return false;
  }

  final int N = cards.length;

  // 如果已经只有一个数了，那么检查一下看看是否
  // 是24
  if (N == 1) {
    return isResult(cards[0]);
  }

  // 否则我们挑两个数，进行加减乘除
  // 其中加法和乘法没有交换的必要
  // 所以我们只需要check两个就可以了
  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      if (judge(getNextCards(cards, i, j,
                cards[i] + cards[j])) || /* 加法 */
          judge(getNextCards(cards, i, j,
                cards[i] * cards[j])) || /* 乘法 */
          notZero(cards[j]) &&
            judge(getNextCards(cards, i, j,
                  cards[i] / cards[j])) || /* 除法 */
          notZero(cards[i]) &&
            judge(getNextCards(cards, i, j,
                  cards[j] / cards[i])) || /* 除法 */
          judge(getNextCards(cards, i, j,
                cards[i] - cards[j])) || /* 减法 */
          judge(getNextCards(cards, i, j,
                cards[j] - cards[i]))    /* 减法 */
      ) {
        return true;
      }
    }
  }

  return false;
}

boolean judgePoint24(int[] cards) {
  if (cards == null) {
    return false;
  }

  double[] dCards = new double[cards.length];
  int to = 0;
  for (int x : cards) {
    dCards[to++] = x;
  }

  return judge(dCards);
}

