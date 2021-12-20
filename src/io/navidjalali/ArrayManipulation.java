package io.navidjalali;

import java.util.List;

/*
Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array
element between two given indices, inclusive. Once all operations have been performed,
return the maximum value in the array.

n = 10
queries = [[1, 5, 3], [4, 8, 7], [6, 9, 1]]

Queries are interpreted as follows:

    a    b    k
    1    5    3
    4    8    7
    6    9    1

Add the values of k between the indices a and b inclusive:
index->	 1    2   3   4   5   6   7   8   9   10
      	[0,   0,  0,  0,  0,  0,  0,  0,  0,  0]
        [3,   3,  3,  3,  3,  0,  0,  0,  0,  0]
      	[3,   3,  3,  10, 10, 7,  7,  7,  0,  0]
      	[3,   3,  3,  10, 10, 8,  8,  8,  1,  0]

index->	 1    2   3   4   5   6   7   8   9   10
      	[0,   0,  0,  0,  0,  0,  0,  0,  0,  0]
      	[3,   0,  0,  0,  0, -3,  0,  0,  0,  0]
      	[3,   0,  0,  7,  0, -3,  0,  0, -7,  0]
      	[3,   0,  0,  7,  0, -2,  0,  0, -7, -1]

running   1
max       10

The largest value is 10 after all operations are performed.
*/

public class ArrayManipulation {
  public static long arrayManipulation(int n, List<List<Integer>> queries) {
    long[] nums = new long[n];

    queries
      .forEach(
        query -> {
          int begin = query.get(0) - 1;
          int end = query.get(1);
          long value = query.get(2);
          nums[begin] += value;
          if (end < n) {
            nums[end] -= value;
          }
        }
      );

    long running = 0;
    long max = 0;

    for (long x : nums) {
      running += x;
      max = Math.max(max, running);
    }

    return max;
  }
}
