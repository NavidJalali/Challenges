package io.navidjalali;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {

  List<Integer> category(String s) {
    List<Integer> result = new ArrayList<>(Collections.nCopies(26, 0));
    char[] chars = s.toCharArray();
    for (char c: chars) {
      result.set(c - 'a', result.get(c - 'a') + 1);
    }
    return result;
  }
  public List<List<String>> groupAnagrams(String[] strs) {

    HashMap<List<Integer>, List<String>> map = new HashMap<>();

    for (String str : strs) {
      List<Integer> category = category(str);
      List<String> entry = map.getOrDefault(category, new ArrayList<>());
      entry.add(str);
      map.put(category, entry);
    }

    return map.values().stream().toList();
  }
}
