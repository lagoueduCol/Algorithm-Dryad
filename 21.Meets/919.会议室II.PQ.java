import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

import org.graalvm.compiler.lir.alloc.lsra.Interval;

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

      // 把所有的会议时间段都按start来排序
      Collections.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) {
          return a.start - b.start;
        }
      });

      // 这里要按照会议室的结束时间来排序
      Queue<Integer> meetingRooms = new PriorityQueue<>((v1, v2) -> v1 - v2);

      for (Interval meeting : intervals) {
        if (!meetingRooms.isEmpty() && meetingRooms.peek() <= meeting.start) {
          // 我们需要把这个会议室的结束时间修改一下
          // 当然，优先级队列里面是不好直接修改元素值的
          // 那我们只能采用先出队，再把当前会议结束时间入队的方式
          meetingRooms.poll();
          meetingRooms.add(meeting.end);
        } else {
          // 如果找不到会议室，那么新开一间
          // 标记其结束时间
          meetingRooms.add(meeting.end);
        }
      }

      return meetingRooms.size();
  }
}



int largestRectangleArea(int[] A) {
  final int N = A == null ? 0 : A.length;
  // 虽然可以用Stack<Integer>，但是这里我们为了更快地操作，我们用
  // 数组模拟栈来运行，因为我们知道最多存放的内容实际上就是N个
  int top = 0;
  // s[top-1]表示栈顶元素
  int[] s = new int[N];
  int ans = 0;
  // 注意，这里我们取到了i == N
  // 按理说，不应该取到i == N的。但是这时候，主要是为了处理这种数组
  // A = [1, 2, 3]
  // 没有任何元素会出栈。
  // 那么最后我们用一个0元素，把所有的元素都削出栈。
  // 这样代码就可以统一处理掉。
  for (int i = 0; i <= N; i++) {
    // 注意：当i == N的时候，x = -1;
    // 比数组中的元素都要小。
    final int x = i == N ? -1 : A[i];
    while (top > 0 && A[s[top - 1]] > x) {
      // 计算以A[s[top]]的元素的高度的矩形。
      final int height = A[s[--top]];
      // i元素要将index = s[top-1]的元素出栈。
      // 那么根据性质2/3：
      // 此时A[s[top-1] .... i) 这个区间里面的元素都是
      // 大于A[s[top-1]]的
      final int rightPos = i;
      // 这里需要使用性质1.
      // 注意：当栈中一个元素都没有的时候，要取-1
      final int leftPos = top > 0 ? s[top - 1] : -1;
      final int width = rightPos - leftPos - 1;
      final int area = height * width;
      ans = Math.max(ans, area);
    }
    s[top++] = i;
  }
  return ans;
}