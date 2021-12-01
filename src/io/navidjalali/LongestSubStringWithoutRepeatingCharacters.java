package io.navidjalali;

import java.util.HashSet;

/*
Given a string s, find the length of the longest substring without repeating characters.

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Approach: Use a sliding window.
*/

class LongestSubStringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String str) {
    int size = str.length();
    HashSet<Character> currentWindowCharset = new HashSet<>();
    int left = 0;
    int max = 0;

    for (int right = 0; right < size; right++) {
      char current = str.charAt(right);

      while (currentWindowCharset.contains(current)) {
        currentWindowCharset.remove(str.charAt(left));
        left++;
      }

      currentWindowCharset.add(current);
      max = Math.max(max, right - left + 1);
    }

    return max;
  }
}
