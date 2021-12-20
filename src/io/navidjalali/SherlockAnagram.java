package io.navidjalali;

import java.util.*;

public class SherlockAnagram {
  public static int sherlockAndAnagramsUnbounded(String s) {
    HashMap<String, Integer> substringCount = new HashMap<>();

    for (int i = 0; i <= s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {

        char[] chars = s.substring(i, j).toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);

        substringCount.put(sorted, substringCount.getOrDefault(sorted, 0) + 1);
      }
    }

    return substringCount
      .values()
      .stream()
      .map(i -> i * (i - 1) / 2)
      .reduce(Integer::sum)
      .orElse(0);
  }

  public static int sherlockAndAnagramsLowercaseASCII(String s) {
    HashMap<List<Integer>, Integer> substringCount = new HashMap<>();

    for (int i = 0; i <= s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {

        char[] chars = s.substring(i, j).toCharArray();
        List<Integer> record = new ArrayList<>(Collections.nCopies(26, 0));

        for (char c : chars) {
          int index = (int) c - (int) 'a';
          record.set(index, record.get(index) + 1);
        }

        substringCount.put(record, substringCount.getOrDefault(record, 0) + 1);
      }
    }

    return substringCount
      .values()
      .stream()
      .map(i -> i * (i - 1) / 2)
      .reduce(Integer::sum)
      .orElse(0);
  }
}
