package io.navidjalali;

/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the
height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are
greater than the other envelope's width and height.
Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
Note: You cannot rotate an envelope.

Example 1:
Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3 ([2,3] => [5,4] => [6,7])

Example 2:
Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1 ([1, 1])
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class RussianDollEnvelopes {
  public int maxEnvelopes(int[][] envelopes) {
    Arrays.sort(envelopes, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
    return longestDecreasingSubSequence(Arrays.stream(envelopes).mapToInt(e -> e[1]).toArray());
  }

  public int longestDecreasingSubSequence(int[] nums) {
    if (nums.length == 0) return 0;

    ArrayList<Stack<Integer>> piles = new ArrayList<>();
    piles.add(new Stack<>());

    for (int num: nums) {

      int left = 0;
      int right = piles.size() - 1;

      Integer requiredPile = null;

      while (left <= right) {
        int mid = left + (right - left) / 2;

        if (piles.get(mid).isEmpty()) {
          requiredPile = mid;
          break;
        }

        if (piles.get(mid).peek() <= num) {
          requiredPile = mid;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }

      if (requiredPile == null) {
        Stack<Integer> s = new Stack<>();
        s.push(num);
        piles.add(s);
      } else {
        piles.get(requiredPile).push(num);
      }

    }

    return piles.size();
  }

  public static void main(String[] args) {
    RussianDollEnvelopes rde = new RussianDollEnvelopes();
    int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
    System.out.println(rde.maxEnvelopes(envelopes));
  }
}
