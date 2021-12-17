package io.navidjalali;

import java.util.List;

/*
It is New Year's Day and people are in line for the Wonderland roller coaster ride.
Each person wears a sticker indicating their initial position in the queue from 1 to n.
Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker.
One person can bribe at most two others.

Determine the minimum number of bribes that took place to get to a given queue order.
Print the number of bribes, or, if anyone has bribed more than two people, print Too chaotic.

[2, 1, 5, 3, 4] -> 3
[2, 5, 1, 3, 4] -> Too chaotic

Approach:
We have to loop backwards because otherwise we might think things are chaotic when they are not.
Check if things are out of place.
If they are, check if one swap or two swaps will put them back in place.
*/

public class NewYearChaos {
  public static void minimumBribes(List<Integer> q) {
    int swaps = 0;
    for (int i = q.size() - 1; i >= 0; i--) {
      if (q.get(i) != i + 1) {
        if (i - 1 >= 0 && q.get(i - 1) == i + 1) {
          final int temp = q.get(i - 1);
          q.set(i - 1, q.get(i));
          q.set(i, temp);
          swaps++;
        } else if (i - 2 >= 0 && q.get(i - 2) == i + 1) {
          final int temp = q.get(i - 2);
          q.set(i - 2, q.get(i - 1));
          q.set(i - 1, q.get(i));
          q.set(i, temp);
          swaps += 2;
        } else {
          System.out.println("Too chaotic");
          return;
        }
      }
    }
    System.out.println(swaps);
  }
}
