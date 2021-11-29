package io.navidjalali;

/*
Given an integer array nums sorted in non-decreasing order,
return an array of the squares of each number sorted in non-decreasing order.

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
*/

class SquaresOfASortedArray {
	public int[] sortedSquares(int[] nums) {
		int length = nums.length;
		int i = 0;
		int j = length - 1;
		int[] output = new int[length];

		for (int k = length - 1; k >= 0; k--) {
			if (Math.abs(nums[i]) > nums[j]) {
				output[k] = nums[i] * nums[i];
				i++;
			} else {
				output[k] = nums[j] * nums[j];
				j--;
			}
		}

		return output;
	}
}
