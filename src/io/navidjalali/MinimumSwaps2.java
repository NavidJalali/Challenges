package io.navidjalali;

/*
You are given an unordered array consisting of consecutive integers [1, 2, 3, ..., n] without any duplicates.
You are allowed to swap any two elements.
Find the minimum number of swaps required to sort the array in ascending order.

Approach: Swap current element to its proper place until its inorder.
*/

public class MinimumSwaps2 {
  static int minimumSwaps(int[] arr) {
    int swaps = 0;
    int i = 0;
    while (i < arr.length) {
      final int current = arr[i];
      if (current == i + 1) {
        i++;
      } else {
        final int temp = arr[current - 1];
        arr[current - 1] = current;
        arr[i] = temp;
        swaps++;
      }
    }
    return swaps;
  }
}
