package io.navidjalali;

public class MissingNumber {

  /*
  Given an array nums containing n distinct numbers in the range [0, n],
  return the only number in the range that is missing from the array.
  */

  public int missingNumber(int[] nums) {
    if (nums.length == 1) {
      return nums[0] == 0 ? 1 : 0;
    }

    int xor = 0;

    for (int i: nums) {
      xor = xor ^ i;
    }

    for (int i = 0; i <= nums.length; i++) {
      xor = xor ^ i;
    }

    return xor;
  }
}
