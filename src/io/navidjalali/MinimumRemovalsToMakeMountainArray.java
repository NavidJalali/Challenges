package io.navidjalali;

/*
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
*/

import java.util.*;

public class MinimumRemovalsToMakeMountainArray {

  int binarySearch(int number, List<Integer> list) {
    int left = 0;
    int right = list.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;


      if (list.get(mid) < number) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }

  List<Integer> longestIncreasingSubsequence(int[] nums) {
    List<Integer> result = new ArrayList<>();
    List<Integer> dp = new ArrayList<>();

    for (int n : nums) {

      int index = binarySearch(n, result);
      if (index == result.size()) {
        result.add(n);
      } else {
        result.set(index, n);
      }

      dp.add(result.size());
    }

    return dp;
  }

  public int minimumMountainRemovals(int[] nums) {
    int length = nums.length;

    // Longest increasing subsequence to the left of element i
    List<Integer> left = longestIncreasingSubsequence(nums);
    // Longest decreasing subsequence to the right of element i
    List<Integer> right = new ArrayList<>(Arrays.stream(nums).boxed().toList());
    Collections.reverse(right);
    right = longestIncreasingSubsequence(right.stream().mapToInt(i -> i).toArray());
    Collections.reverse(right);

    // todo: fill left and right arrays
    int max = 0;

    for (int i = 1; i < length - 1; i++) {
      if (left.get(i) > 1 && right.get(i) > 1) {
        max = Math.max(max, left.get(i) + right.get(i) - 1);
      }
    }

    return length - max;
  }
}
