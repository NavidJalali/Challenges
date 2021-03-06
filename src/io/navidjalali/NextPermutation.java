package io.navidjalali;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.
Input: nums = [1,2,3]
Output: [1,3,2]

Input: nums = [3,2,1]
Output: [1,2,3]

Input: nums = [1,1,5]
Output: [1,5,1]
*/

class NextPermutation {
  public void nextPermutation(int[] nums) {
    if (nums.length <= 1) return;

    int k = nums.length - 2;
    while (k >= 0 && nums[k] >= nums[k + 1]) k--;

    if (k >= 0) {
      int j = nums.length - 1;

      while (nums[j] <= nums[k]) j--;

      swap(nums, k, j);
    }
    reverse(nums, k + 1);
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public void reverse(int[] nums, int start) {
    int i = start;
    int j = nums.length - 1;

    while (i <= j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }
}
