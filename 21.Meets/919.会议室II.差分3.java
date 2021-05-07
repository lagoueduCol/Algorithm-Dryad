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

public class Solution {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: the minimum number of conference rooms required
   */
  public int minMeetingRooms(List<Interval> intervals) {
      // Write your code here
      final int N = intervals == null ? 0 : intervals.size();

      int[] start = new int[N];
      int[] end = new int[N];

      int i = 0;
      for (Interval range: intervals) {
        start[i] = range.start;
        end[i] = range.end;
        i++;
      }

      Arrays.sort(start);
      Arrays.sort(end);

      i = 0;
      int j = 0;

      int pre = 0;
      int ans = 0;
      while (i < N || j < N) {
        if (j >= N || i < N && start[i] < end[j]) {
          // 是个坐标的起始点
          pre++;
          i++;
        } else {
          // 是个坐标的终点
          pre--;
          j++;
        }
        ans = Math.max(ans, pre);
      }

      return ans;
  }
}