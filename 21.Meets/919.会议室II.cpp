/**
 * // 测试平台： https://www.lintcode.com/problem/919/
 *
 * Definition of Interval:
 * classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this->start = start;
 *         this->end = end;
 *     }
 * }
 */

class Solution {
public:
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    int minMeetingRooms(vector<Interval> &intervals) {
        // Write your code here
        const int N = intervals.size();

        sort(intervals.begin(), intervals.end(),
          [](const Interval &a, const Interval &b) {
            return a.end < b.end;
          });

        multiset<int> room_end_time;

        for (auto &meeting: intervals) {
          // 得到会议的起始时间
          const int from = meeting.start;
          const int end = meeting.end;

          // 首先，如果会义室是空的
          if (room_end_time.empty()) {
            room_end_time.insert(end);
            continue;
          }

          auto iter = room_end_time.lower_bound(from);
          // 那么iter 的值是 >= from的
          // 此时，就需要看一下是不是相等的
          // 如果相等，那么就使用这个会议室
          if (iter != room_end_time.end() && *iter == from) {
            room_end_time.erase(iter);
            room_end_time.insert(end);
          } else if (iter == room_end_time.begin() && *iter > from) {
            // 所有的结束时间都比from大了
            room_end_time.insert(end);
          } else {
            iter--;
            room_end_time.erase(iter);
            room_end_time.insert(end);
          }
        }

        return room_end_time.size();
    }
};