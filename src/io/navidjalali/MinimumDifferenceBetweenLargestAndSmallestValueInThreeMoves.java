package io.navidjalali;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
  /*
  You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.

  Return the minimum difference between the largest and smallest value of nums after performing at most three moves.

  Example 1:

  Input: nums = [5,3,2,4]
  Output: 0
  Explanation: Change the array [5,3,2,4] to [2,2,2,2].
  The difference between the maximum and minimum is 2-2 = 0.
  Example 2:

  Input: nums = [1,5,0,10,14]
  Output: 1
  Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
  The difference between the maximum and minimum is 1-0 = 1.
  */

  public int minDifference(int[] nums) {
    if (nums.length <= 4) {
      return 0;
    }

    // The 4 maximum elements
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(4);

    // The 4 minimum elements
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(4, (a, b) -> b - a);

    for (int num : nums) {
      minHeap.offer(num);
      maxHeap.offer(num);

      // Keep the heaps size 4
      if (minHeap.size() > 4) {
        minHeap.poll();
      }

      if (maxHeap.size() > 4) {
        maxHeap.poll();
      }
    }

    int[] minsAndMaxes = new int[8];

    for (int i = 0; i < 4; i++) {
      // Safe to use poll() here because the size of the heap is 4
      minsAndMaxes[3 - i] = maxHeap.poll();
      minsAndMaxes[4 + i] = minHeap.poll();
    }

    int minDiff = Integer.MAX_VALUE;

    for (int i = 0; i < 4; i++) {
      minDiff = Math.min(minDiff, minsAndMaxes[i + 4] - minsAndMaxes[i]);
    }

    return minDiff;
  }
}
