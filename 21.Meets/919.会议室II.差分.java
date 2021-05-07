import java.util.ArrayList;
import java.util.Collections;

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Counter extends HashMap<Integer, Integer> {
    public int get(Integer k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(Integer k, int v) {
        put(k, get(k) + v);
        if (get(k) <= 0) {
            remove(k);
        }
    }
}

public class Solution {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: the minimum number of conference rooms required
   */
  public int minMeetingRooms(List<Interval> intervals) {
      // Write your code here
      // 利用Hash表生成A[]数组
      Counter A = new Counter();

      List<Integer> idx = new ArrayList<>();

      for (Interval range: intervals) {
        final int start = range.start;
        final int end = range.end;
        A.add(start, 1);
        A.add(end, -1);

        idx.add(start);
        idx.add(end);
      }

      Collections.sort(idx);

      int pre = 0;
      int ans = 0;
      for (Integer i: idx) {
        pre += A.get(i);
        ans = Math.max(ans, pre);
      }

      return ans;
  }
}