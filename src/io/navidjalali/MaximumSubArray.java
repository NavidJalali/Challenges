package io.navidjalali;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number
which has the largest sum and return its sum.
A subarray is a contiguous part of an array.
*/

public class MaximumSubArray {
  public int maxSubArray(int[] nums) {
    int size = nums.length;
    int memo = nums[0];
    int max = nums[0];
    for (int i = 1; i < size; i++) {
      memo = Math.max(nums[i], memo + nums[i]);
      max = Math.max(max, memo);
    }
    return max;
  }
}
