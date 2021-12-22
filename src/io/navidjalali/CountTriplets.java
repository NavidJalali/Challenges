package io.navidjalali;

import java.util.HashMap;
import java.util.List;

public class CountTriplets {
  static long countTriplets(List<Long> arr, long r) {
    HashMap<Long, Long> pairCandidate = new HashMap<Long, Long>();
    HashMap<Long, Long> tripletCandidate = new HashMap<Long, Long>();
    long tripletCount = 0;

    for (long n : arr) {
      if (tripletCandidate.containsKey(n)) {
        tripletCount += tripletCandidate.get(n);
      }

      if (pairCandidate.containsKey(n)) {
        tripletCandidate.put(n * r, tripletCandidate.getOrDefault(n * r, 0L) + pairCandidate.get(n));
      }

      pairCandidate.put(n * r, pairCandidate.getOrDefault(n * r, 0L) + 1);
    }

    return tripletCount;
  }
}
