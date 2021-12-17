package io.navidjalali;

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can
rob tonight without alerting the police.

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Approach: f(n) = Max(a(n) + f(n - 2), a(n - 1))
*/

class HouseRobber {
  public int rob(int[] nums) {
    int length = nums.length;
    if (length == 1) return nums[0];
    int[] memo = new int[2];
    memo[0] = nums[0];
    memo[1] = Math.max(nums[0], nums[1]);
    int max = Math.max(nums[0], nums[1]);

    for (int i = 2; i < length; i++) {
      int maxUntilNow = Math.max(nums[i] + memo[0], memo[1]);
      memo[0] = memo[1];
      memo[1] = maxUntilNow;
      max = Math.max(max, maxUntilNow);
    }

    return max;
  }
}
