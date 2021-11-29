package io.navidjalali;

import java.util.List;
import java.util.stream.Collectors;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

"23"
[ad, ae, af, bd, be, bf, cd, ce, cf]

    1       2       3
           abc     def
    4       5       6
   ghi     jkl     mno
    7       8       9
  pqrs     tuv    wxyz
*/

class LetterCombinationsOfAPhoneNumber {
  public static List<List<String>> letters = List.of(
    List.of("a", "b", "c"),
    List.of("d", "e", "f"),
    List.of("g", "h", "i"),
    List.of("j", "k", "l"),
    List.of("m", "n", "o"),
    List.of("p", "q", "r", "s"),
    List.of("t", "u", "v"),
    List.of("w", "x", "y", "z")
  );

  public List<String> letterCombinations(String digits) {
    return new StringBuilder(digits)
      .reverse()
      .codePoints()
      .map(i -> i - '0' - 2)
      .mapToObj(i -> letters.get(i))
      .reduce((acc, next) ->
        next
          .stream()
          .flatMap(c -> acc.stream().map(c::concat))
          .collect(Collectors.toList())
      )
      .orElse(List.of());
  }
}
