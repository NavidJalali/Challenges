package io.navidjalali;

// You are climbing a staircase. It takes n steps to reach the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

import java.util.HashMap;

public class ClimbingStairs {
  public static double Phi = (1 + Math.sqrt(5)) / 2;
  public static double phi = (1 - Math.sqrt(5)) / 2;
  public static int fib(int n) {
    return Math.toIntExact(Math.round((Math.pow(Phi, n) - Math.pow(phi, 5)) / Math.sqrt(5)));
  }

  public int climbStairsFast(int n) {
    return fib(n + 1);
  }

  public int climbStairsSlow(int n) {
    int m0 = 1;
    int m1 = 1;
    for (int i = 2; i <= n; i++) {
      int res = m0 + m1;
      m0 = m1;
      m1 = res;
    }

    return m1;
  }
}
