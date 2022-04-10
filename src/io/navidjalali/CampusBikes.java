package io.navidjalali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampusBikes {

  // https://ttzztt.gitbooks.io/lc/content/sort/bucket-sort/campus-bikes.html

  public static class Pair {
    int x;
    int y;

    public int Manhattan(Pair other) {
      return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  public int[] assignBikes(Pair[] workers, Pair[] bikes) {
    int workerSize = workers.length;
    int bikeSize = bikes.length;
    int[] result = new int[workerSize];

    boolean[] workerHasAssignedBike = new boolean[workerSize];
    boolean[] bikeHasAssignedWorker = new boolean[bikeSize];

    List<Pair>[] buckets = new List[2000];
    Arrays.fill(buckets, null);

    for (int i = 0; i < workerSize; i++) {
      for (int j = 0; j < bikeSize; j++) {
        int distance = workers[i].Manhattan(bikes[j]);
        if (buckets[distance] == null) {
          buckets[distance] = new ArrayList<Pair>();
        }
        buckets[distance].add(new Pair(i, j));
      }
    }

    for (List<Pair> bucket : buckets) {
      if (bucket != null) {
        for (Pair pair : bucket) {
          if (!workerHasAssignedBike[pair.x] && !bikeHasAssignedWorker[pair.y]) {
            result[pair.x] = pair.y;
            workerHasAssignedBike[pair.x] = true;
            bikeHasAssignedWorker[pair.y] = true;
          }
        }
      }
    }

    return result;
  }
}
