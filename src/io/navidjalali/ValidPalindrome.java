package io.navidjalali;

public class ValidPalindrome {
  private int nextValidChar(char[] chars, int index, boolean countDown) {
    while (index >= 0 && index < chars.length) {
      if (Character.isLetterOrDigit(chars[index])) {
        break;
      } else {
        if (countDown)
          index--;
        else
          index++;
      }
    }
    return index;
  }

  public boolean isPalindrome(String s) {
    if (s.length() == 0) {
      return true;
    }

    char[] chars = s.toCharArray();
    int left = nextValidChar(chars, 0, false);
    int right = nextValidChar(chars, chars.length - 1, true);

    while (left < right) {
      if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
        return false;
      }
      left = nextValidChar(chars, left + 1, false);
      right = nextValidChar(chars, right - 1, true);
    }

    return true;
  }
}
