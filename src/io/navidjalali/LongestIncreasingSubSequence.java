package io.navidjalali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without
changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Approach: The n^2 approach is basically a DFS with cache.
Approach 2: The nlogn approach is use patience sort (from the patience card "game"). Each pile is part of an
increasing subsequence whose length is the pile index. If a pointer points to the last element of the previous
pile at the time of insertion then it forms a chain of increasing card numbers. Also patience sort is greedy and
forms the least amount of piles.
*/

public class LongestIncreasingSubSequence {
  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;

    int[] lis = new int[nums.length];
    Arrays.fill(lis, 1);

    int max = 1;

    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] < nums[j]) {
          lis[i] = Math.max(lis[i], lis[j] + 1);
        }
      }
      max = Math.max(max, lis[i]);
    }

    return max;
  }

  public int lengthOfLISFast(int[] nums) {
    ArrayList<Stack<Integer>> piles = new ArrayList<Stack<Integer>>();

    piles.set(0, new Stack<Integer>());

    for (int num: nums) {

      int left = 0;
      int right = piles.size() - 1;

      Integer requiredPile = null;

      while (left <= right) {
        int mid = left + (right - left) / 2;


        if (piles.get(mid).isEmpty()) {
          requiredPile = mid;
          break;
        }

        if (piles.get(mid).peek() >= num) {
          requiredPile = mid;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }

      if (requiredPile == null) {
        Stack<Integer> s = new Stack<>();
        s.push(num);
        piles.add(s);
      } else {
        piles.get(requiredPile).push(num);
      }
    }
    return piles.size();
  }
}
