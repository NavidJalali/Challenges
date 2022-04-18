package io.navidjalali;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the
product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and O(1) space (except for result array)
without using the division operation.
*/

public class ProductOfArrayExceptSelf {
  public int[] productExceptSelf(int[] nums) {
    int[] result = new int[nums.length];
    int running = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      result[i] = running;
      running *= nums[i];
    }
    running = 1;
    for (int i = 0; i < nums.length; i++) {
      result[i] *= running;
      running *= nums[i];
    }
    return result;
  }
}
