package io.navidjalali;

/*
Given n non-negative integers a1, a2, ..., an
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.
*/

class ContainerWithMostWater {
	public int maxArea(int[] height) {

		int start = 0;
		int end = height.length - 1;
		int max = 0;
		while (start <= end) {
			int valueAtEnd = height[end];
			int valueAtStart = height[start];
			max = Math.max(max, (end - start) * Math.min(valueAtEnd, valueAtStart));
			if (valueAtEnd > valueAtStart) start++;
			else end--;
		}
		return max;
	}
}
