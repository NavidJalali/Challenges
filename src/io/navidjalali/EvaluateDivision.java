package io.navidjalali;

import java.util.*;

/*
You are given an array of variable pairs equations and an array of real numbers values,
where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find
the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in
division by zero and that there is no contradiction.

Input:
equations = [["a","b"],["b","c"]],
values = [2.0,3.0],
queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
  */

public class EvaluateDivision {
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

    for (int i = 0; i < values.length; i++) {
      List<String> pair = equations.get(i);
      String num = pair.get(0);
      String den = pair.get(1);

      graph.putIfAbsent(num, new HashMap<>());
      graph.putIfAbsent(den, new HashMap<>());

      graph.get(num).put(den, values[i]);
      graph.get(den).put(num, 1 / values[i]);
    }

    double[] result = new double[queries.size()];

    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String num = query.get(0);
      String den = query.get(1);

      if (!graph.containsKey(num) || !graph.containsKey(den)) {
        result[i] = -1;
      } else if (num.equals(den)) {
        result[i] = 1;
      } else {
        result[i] = dfs(graph, num, den, 1, new HashSet<>());
      }
    }

    return result;
  }

  private double dfs(HashMap<String, HashMap<String, Double>> graph,
                     String start,
                     String end,
                     double accumulator,
                     Set<String> visited) {
    visited.add(start);
    HashMap<String, Double> neighbors = graph.get(start);

    if (neighbors.containsKey(end)) {
      return accumulator * neighbors.get(end);
    } else {
      for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
        if (!visited.contains(entry.getKey())) {
          double result = dfs(graph, entry.getKey(), end, accumulator * entry.getValue(), visited);
          if (result != -1) {
            graph.get(start).put(end, result);
            return result;
          }
        }
      }
      return -1;
    }
  }
}
