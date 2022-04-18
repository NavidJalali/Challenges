package io.navidjalali;

/*
Given an integer array nums, find a contiguous non-empty subarray within the array
that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.
*/

public class MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    int size = nums.length;
    int memoMax;
    int memoMin;
    int totalMax;

    memoMax = memoMin = totalMax = nums[0];

    for (int i = 1; i < size; i++) {
      int a = Math.max(nums[i], Math.max(nums[i] * memoMax, nums[i] * memoMin));
      int b = Math.min(nums[i], Math.min(nums[i] * memoMax, nums[i] * memoMin));
      memoMax = Math.max(a, b);
      memoMin = Math.min(a, b);
      totalMax = Math.max(memoMax, totalMax);
    }

    return totalMax;
  }
}
