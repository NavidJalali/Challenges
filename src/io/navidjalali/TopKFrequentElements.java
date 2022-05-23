package io.navidjalali;

import java.util.*;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Example:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Explanation: You can use heaps or bucket sort.
*/

public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> counts = new HashMap<>();

    for (int n : nums) {
      counts.put(n, counts.getOrDefault(n, 0) + 1);
    }

    List<List<Integer>> freq = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      freq.add(new ArrayList<>());
    }

    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      freq.get(entry.getValue() - 1).add(entry.getKey());
    }

    int[] result = new int[k];

    for (int i = freq.size() - 1; i >= 0 && k > 0; i--) {
      List<Integer> list = freq.get(i);
      for (int j = 0; j < list.size() && k > 0; j++) {
        result[--k] = list.get(j);
      }
    }

    return result;
  }
}
