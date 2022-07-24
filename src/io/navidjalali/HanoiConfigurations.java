package io.navidjalali;

import java.util.*;

/*
  Minimum path from any Hanoi configuration to any other Hanoi configuration
*/
public class HanoiConfigurations {
  public static class HanoiConfig {
    public List<Integer> A;
    public List<Integer> B;
    public List<Integer> C;

    public HanoiConfig(List<Integer> A, List<Integer> B, List<Integer> C) {
      this.A = A;
      this.B = B;
      this.C = C;
    }

    @Override
    public int hashCode() {
      return A.hashCode() + B.hashCode() + C.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final HanoiConfig other = (HanoiConfig) obj;

      return A.equals(other.A) && B.equals(other.B) && C.equals(other.C);
    }

    HanoiConfig applyMove(char[] move) {
      List<Integer> A2 = new ArrayList<>(this.A);
      List<Integer> B2 = new ArrayList<>(this.B);
      List<Integer> C2 = new ArrayList<>(this.C);

      int removed = switch (move[0]) {
        case 'A' -> A2.remove(0);
        case 'B' -> B2.remove(0);
        case 'C' -> C2.remove(0);
        default -> throw new IllegalArgumentException();
      };

      switch (move[1]) {
        case 'A' -> A2.add(0, removed);
        case 'B' -> B2.add(0, removed);
        case 'C' -> C2.add(0, removed);
      }

      return new HanoiConfig(A2, B2, C2);
    }

    public List<char[]> validMoves() {
      List<char[]> moves = new ArrayList<>();

      Optional<Integer> a = A.stream().findFirst();
      Optional<Integer> b = B.stream().findFirst();
      Optional<Integer> c = C.stream().findFirst();

      if (a.isPresent()) {
        if (b.isPresent()) {
          if (a.get() < b.get()) {
            moves.add(new char[] {'A', 'B'});
          }
        } else {
          moves.add(new char[] {'A', 'B'});
        }

        if (c.isPresent()) {
          if (a.get() < c.get()) {
            moves.add(new char[] {'A', 'C'});
          }
        } else {
          moves.add(new char[] {'A', 'C'});
        }
      }

      if (b.isPresent()) {
        if (a.isPresent()) {
          if (b.get() < a.get()) {
            moves.add(new char[] {'B', 'A'});
          }
        } else {
          moves.add(new char[] {'B', 'A'});
        }

        if (c.isPresent()) {
          if (b.get() < c.get()) {
            moves.add(new char[] {'B', 'C'});
          }
        } else {
          moves.add(new char[] {'B', 'C'});
        }
      }

      if (c.isPresent()) {
        if (a.isPresent()) {
          if (c.get() < a.get()) {
            moves.add(new char[] {'C', 'A'});
          }
        } else {
          moves.add(new char[] {'C', 'A'});
        }

        if (b.isPresent()) {
          if (c.get() < b.get()) {
            moves.add(new char[] {'C', 'B'});
          }
        } else {
          moves.add(new char[] {'C', 'B'});
        }
      }

      return moves;
    }

    public String toString() {
      return A.toString() + "\n" + B.toString() + "\n" + C.toString();
    }
  }

  public List<char[]> minimumMovesToReachConfig(HanoiConfig initial, HanoiConfig target) {
    HashSet<HanoiConfig> visited = new HashSet<>();
    Queue<HanoiConfig> queue = new LinkedList<>();

    Queue<List<char[]>> moves = new LinkedList<>();

    HanoiConfig current = initial;
    List<char[]> currentMoves = new ArrayList<>();

    while (!current.equals(target)) {
      visited.add(current);
      List<char[]> validMoves = current.validMoves();
      for (char[] move : validMoves) {
        HanoiConfig next = current.applyMove(move);
        if (!visited.contains(next)) {
          queue.add(next);
          List<char[]> movesToAdd = new ArrayList<>(currentMoves);
          movesToAdd.add(move);
          moves.add(movesToAdd);
        }
      }

      current = queue.remove();
      currentMoves = moves.remove();
    }

    return currentMoves;
  }

  public static void main(String[] args) {
    HanoiConfigurations hc = new HanoiConfigurations();

    List<Integer> A = List.of(2);
    List<Integer> B = List.of(1);
    List<Integer> C = List.of(0);

    HanoiConfig initial = new HanoiConfig(A, B, C);

    List<Integer> A2 = List.of(0, 2);
    List<Integer> B2 = List.of();
    List<Integer> C2 = List.of(1);

    HanoiConfig target = new HanoiConfig(A2, B2, C2);

    List<char[]> moves = hc.minimumMovesToReachConfig(initial, target);
    System.out.println(moves.stream().map(Arrays::toString).toList());
  }
}
