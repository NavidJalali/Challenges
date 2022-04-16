package io.navidjalali;

/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space and linear time.

Approach: Since the array contains n + 1 numbers and all lie between 1 to n inclusive it forms a linked list.
We are looking for the first point where a cycle starts.
Floyd's linked list cycle finder: For this you can use a slow and fast pointer. Once they meet you start another
slow pointer from the head and move both pointers at the same pace. They will meet at the start of the cycle.
This question is extra bullshit.
*/

public class FindTheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int slow = nums[0];
    int fast = nums[0];
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    int pointer = nums[0];
    while (pointer != slow) {
      slow = nums[slow];
      pointer = nums[pointer];
    }
    return slow;
  }
}
