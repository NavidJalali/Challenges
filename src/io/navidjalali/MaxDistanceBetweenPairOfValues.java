package io.navidjalali;

/*
You are given two non-increasing 0-indexed integer arrays nums1 and nums2 .
A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length,
is valid if both i <= j and nums1[i] <= nums2[j].
The distance of the pair is j - i .
Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
*/

class MaxDistanceBetweenPairOfValues {

	public int maxDistance(int[] nums1, int[] nums2) {
		int i = 0;
		int j = 0;
		int maxDist = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] <= nums2[j]) {
				maxDist = Math.max(maxDist, j - i);
				j++;
			} else {
				i++;
				j = Math.max(i, j);
			}
		}
		return maxDist;
	}
}
