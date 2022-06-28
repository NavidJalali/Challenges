package io.navidjalali;

import java.util.Arrays;
import java.util.List;

public class MeetingRooms2 {
  public static class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  int minimumMeetingRooms(List<Interval> intervals) {
    int[] starts = intervals.stream().mapToInt(i -> i.start).toArray();
    Arrays.sort(starts);
    int[] ends = intervals.stream().mapToInt(i -> i.end).toArray();
    Arrays.sort(ends);

    int rooms = 0;
    int max = 0;

    int i = 0, j = 0;

    while (i < intervals.size()) {
      if (starts[i] < ends[j]) {
        rooms++;
        i++;
      } else {
        rooms--;
        j++;
      }
      max = Math.max(max, rooms);
    }

    return max;
  }
}
