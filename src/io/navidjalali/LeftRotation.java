package io.navidjalali;

import java.util.List;

/*
A left rotation operation on an array shifts each of the array's elements 1 unit to the left.
For example, if 2 left rotations are performed on array [1, 2, 3, 4, 5], then the array would become [3, 4, 5, 1, 2].
Note that the lowest index item moves to the highest index in a rotation. This is called a circular array.

Given an array a of n integers and a number, d, perform d left rotations on the array.
Return the updated array to be printed as a single line of space-separated integers.

Approach:
O(1) space. O(n) time.
Reverse the whole thing, then reverse one chunk, then reverse another chunk.
*/

public class LeftRotation {

  private static List<Integer> reverse(List<Integer> list, int startIndex, int endIndex) {
    final int length = list.size();

    if (startIndex >= length || startIndex < 0 || endIndex >= length || endIndex < 0) {
      throw new IllegalArgumentException("Invalid bounds");
    }

    while (startIndex < endIndex) {
      final int temp = list.get(startIndex);
      list.set(startIndex, list.get(endIndex));
      list.set(endIndex, temp);

      startIndex++;
      endIndex--;
    }

    return list;
  }

  public static List<Integer> rotLeft(List<Integer> a, int d) {
    final int length = a.size();
    final int r = d % length;

    if (r == 0) return a;

    reverse(a, 0, length - 1);
    reverse(a, 0, length - r - 1);
    reverse(a, length - r, length - 1);
    return a;
  }
}
