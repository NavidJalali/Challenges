package io.navidjalali;

/*
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.

*/

class IsPalindrome {
  public boolean isPalindrome(int x) {
    if (x < 0) return false;
    int reversed = 0;
    int original = x;
    while (original > 0) {
      reversed = reversed * 10 + original % 10;
      original /= 10;
    }

    return x == reversed;
  }
}