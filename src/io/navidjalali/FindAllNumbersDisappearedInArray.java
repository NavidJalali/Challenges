package io.navidjalali;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindAllNumbersDisappearedInArray {
  public List<Integer> findDisappearedNumbers(int[] nums) {
    int size = nums.length;
    List<Integer> result = new ArrayList();
    for (int i = 1; i <= size; i++) {
      result.add(i);
    }
    for (int n : nums) {
      result.set(n - 1, null);
    }

    return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
  }
}
