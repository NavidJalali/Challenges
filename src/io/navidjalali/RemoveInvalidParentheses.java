package io.navidjalali;

/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make
the input string valid.

Return all the possible results. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]
*/

import java.util.HashSet;
import java.util.List;

public class RemoveInvalidParentheses {
  public int minRemovalsToMakeValid(String s) {
    int open = 0;
    int remove = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') open++;
      else if (c == ')') {
        if (open > 0) open--;
        else remove++;
      }
    }
    remove += open;
    return remove;
  }

  public List<String> removeInvalidParentheses(String s) {
    int remove = minRemovalsToMakeValid(s);
    HashSet<String> result = new HashSet<>();
    dfs(s, remove, 0, 0, "", result);
    return result.stream().toList();
  }

  private void dfs(String s, int remove, int index, int open, String accumulated, HashSet<String> result) {
    if (index == s.length()) {
      if (open == 0 && remove == 0) result.add(accumulated);
      return;
    }

    if (remove < 0) return;

    char c = s.charAt(index);

    if (c == '(') {
      dfs(s, remove, index + 1, open + 1, accumulated + '(', result);
      dfs(s, remove - 1, index + 1, open, accumulated, result);
    } else if (c == ')') {
      if (open > 0) dfs(s, remove, index + 1, open - 1, accumulated + ')', result);
      dfs(s, remove - 1, index + 1, open, accumulated, result);
    } else {
      dfs(s, remove, index + 1, open, accumulated + c, result);
    }
  }
}
