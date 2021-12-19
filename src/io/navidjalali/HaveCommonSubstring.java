package io.navidjalali;

import java.util.HashSet;
import java.util.List;

public class HaveCommonSubstring {
  public static boolean twoStrings(String s1, String s2) {
    HashSet<Character> set = new HashSet<Character>();

    for (char c : s1.toCharArray()) {
      set.add(c);
    }

    for (char c : s2.toCharArray()) {
      if (set.contains(c)) {
        return true;
      }
    }

    return false;
  }
}
