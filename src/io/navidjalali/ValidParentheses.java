package io.navidjalali;

import java.util.Stack;

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
 1. Open brackets must be closed by the same type of brackets.
 2. Open brackets must be closed in the correct order.
*/

class ValidParentheses {

  public char matching(char c) {
    if (c == '{') return '}';
    if (c == '(') return ')';
    if (c == '[') return ']';
    throw new IllegalArgumentException();
  }

  public boolean isOpening(char c) {
    return (c == '{') || (c == '(') || (c == '[');
  }

  public boolean isClosing(char c) {
    return (c == '}') || (c == ')') || (c == ']');
  }

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (isOpening(c)) {
        stack.push(matching(c));
      } else if (isClosing(c)) {
        if (stack.isEmpty()) return false;
        char top = stack.pop();
        if (top != c) return false;
      } else {
        throw new IllegalArgumentException();
      }
    }

    return stack.isEmpty();
  }
}

