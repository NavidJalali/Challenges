package io.navidjalali;

/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is
the number of 1's in the binary representation of i.
*/
public class CountingBits {
  public int[] myDumbCountBits(int n) {
    if (n == 0) {
      return new int[]{0};
    } else {
      int[] result = new int[n + 1];
      result[0] = 0;
      for (int i = 1; i <= n; i++) {
        int mask = (1 << (int) (Math.log(i) / Math.log(2))) - 1;
        result[i] = 1 + result[i & mask];
      }
      return result;
    }
  }

  public int[] betterCountBits(int n) {
    int[] result = new int[n + 1];
    if (n == 0) {
      return result;
    }
    for (int i = 1; i <= n; i++) {
      result[i] = (i % 2 == 0) ? result[i / 2] : 1 + result[i / 2];
    }
    return result;
  }
}
