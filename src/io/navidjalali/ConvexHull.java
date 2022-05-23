package io.navidjalali;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class ConvexHull {
  public static class Vector2 {
    public double x, y;

    public Vector2(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public Vector2 add(Vector2 v) {
      return new Vector2(x + v.x, y + v.y);
    }

    public Vector2 subtract(Vector2 v) {
      return new Vector2(x - v.x, y - v.y);
    }

    public double cross(Vector2 v) {
      return x * v.y - y * v.x;
    }

    public boolean equals(Object v) {
      if (v instanceof Vector2 v2) {
        return x == v2.x && y == v2.y;
      } else {
        return false;
      }
    }

    public int hashCode() {
      int hash = 17;
      hash = hash * 31 + Double.hashCode(x);
      hash = hash * 31 + Double.hashCode(y);
      return hash;
    }

    public String toString() {
      return "(" + x + ", " + y + ")";
    }

    public static Vector2 random(double minX, double maxX, double minY, double maxY) {
      return new Vector2(Math.random() * (maxX - minX) + minX, Math.random() * (maxY - minY) + minY);
    }
  }

  private static List<Vector2> sortedPoints(List<Vector2> points, Vector2 reference) {
    return points.stream().sorted(
        (a, b) -> {
          // Reference point should always be in the beginning of the list
          if (a.equals(reference)) {
            return -1;
          }

          if (b.equals(reference)) {
            return 1;
          }

          Vector2 u = a.subtract(reference);
          Vector2 v = b.subtract(reference);

          // Cross product is 0 if the points are collinear
          // Positive if counter-clockwise and negative if clockwise
          double cross = u.cross(v);

          if (cross == 0) {
            // In case points are collinear, we need to check the distance
            if (Double.compare(a.x, b.x) == 0) {
              return a.y < b.y ? -1 : 1;
            } else {
              return a.x < b.x ? -1 : 1;
            }
          } else {
            return cross > 0 ? -1 : 1;
          }
        }
    ).toList();
  }

  public static List<Vector2> convexHull(List<Vector2> points) {
    if (points.size() <= 3) {
      return points;
    }

    Stack<Vector2> stack = new Stack<>();

    Vector2 lowestY = points.get(0);
    for (Vector2 point : points) {
      if (point.y < lowestY.y) {
        lowestY = point;
      }
    }

    List<Vector2> sortedPoints = sortedPoints(points, lowestY);

    stack.push(sortedPoints.get(0));
    stack.push(sortedPoints.get(1));

    for (int i = 2; i < sortedPoints.size(); i++) {
      Vector2 next = sortedPoints.get(i);
      Vector2 top = stack.pop();

      while (stack.size() > 0 && next.subtract(top).cross(top.subtract(stack.peek())) >= 0) {
        top = stack.pop();
      }

      stack.push(top);
      stack.push(next);
    }

    // Last pushed element could be collinear with the second to last and the very first element
    // Remove the last one if they are collinear
    Vector2 top = stack.pop();
    if (lowestY.subtract(top).cross(top.subtract(stack.peek())) < 0) {
      stack.push(top);
    }

    return new ArrayList<>(stack);
  }

  public static void prettyPrint(List<Vector2> points) {
    StringBuilder sb = new StringBuilder();
    for (Vector2 point : points) {
      sb.append(point.toString());
      sb.append(", ");
    }

    sb.delete(sb.length() - 2, sb.length());

    System.out.println(sb.toString());
  }

  public static void main(String[] args) {
    double min = -5;
    double max = 5;
    List<Vector2> input = IntStream.range(0, 8).mapToObj(i -> Vector2.random(min, max, min, max)).toList();
    prettyPrint(input);
    List<Vector2> hull = convexHull(input);
    prettyPrint(hull);
  }
}
