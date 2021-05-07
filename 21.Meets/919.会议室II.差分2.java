
public class Solution {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: the minimum number of conference rooms required
   */
  public int minMeetingRooms(List<Interval> intervals) {

    List<Integer> item = new ArrayList<>();
    for (Interval range: intervals) {
      item.add(range.start);
      item.add(0 - range.end);
    }

    Collections.sort(item, new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
          return Math.abs(a) - Math.abs(b);
        }
    });

    int ans = 0;
    int pre = 0;
    for (int i = 0; i < item.size(); i++) {
      if (item.get(i) >= 0) {
        pre++;
      } else {
        pre--;
      }
      ans = Math.max(ans, pre);
    }

    return ans;
  }
}