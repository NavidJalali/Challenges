package io.navidjalali;

/*
Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
i.e. nums[left] + nums[left + 1] + ... + nums[right]
*/

public class RangeSumQuery {
  class NumArray {

    private final int[] partials;

    public NumArray(int[] nums) {
      partials = new int[nums.length];
      partials[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        partials[i] = nums[i] + partials[i - 1];
      }
    }

    public int sumRange(int left, int right) {
      int low = left == 0 ? 0 : partials[left - 1];
      return partials[right] - low;
    }
  }

  public void test(String[] args) {
    int[] nums = {-2, 0, 3, -5, 2, -1};
    NumArray numArray = new NumArray(nums);
    System.out.println(numArray.sumRange(0, 2)); // return (-2) + 0 + 3 = 1
    System.out.println(numArray.sumRange(2, 5)); // return 3 + (-5) + 2 + (-1) = -1
    System.out.println(numArray.sumRange(0, 5)); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
  }
}
