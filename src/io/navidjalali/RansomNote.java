package io.navidjalali;

import java.util.HashMap;
import java.util.List;

/*
Harold is a kidnapper who wrote a ransom note,
but now he is worried it will be traced back to him through his handwriting.
He found a magazine and wants to know if he can cut out whole words from it and use them to
create an untraceable replica of his ransom note. The words in his note are case-sensitive,
and he must use only whole words available in the magazine.
He cannot use substrings or concatenation to create the words he needs.

Given the words in the magazine and the words in the ransom note,
return true if he can replicate his ransom note exactly using whole words from the magazine; otherwise, return false.
*/

class RansomNote {
  public static boolean checkMagazine(List<String> magazine, List<String> note) {
    final HashMap<String, Integer> words = new HashMap<>();

    for (String word : magazine) {
      words.put(word, words.getOrDefault(word, 0) + 1);
    }

    for (String word : note) {
      if (!words.containsKey(word) || words.get(word) <= 0) {
        return false;
      } else {
        words.put(word, words.get(word) - 1);
      }
    }

    return true;
  }
}
