package io.navidjalali;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CourseSchedule {

  /*
  There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
  You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
  take course bi first if you want to take course ai.

  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
  Return true if you can finish all courses. Otherwise, return false.
  */

  public boolean canFinish(int numCourses, int[][] prerequisites) {

    HashMap<Integer, List<Integer>> preReqMap = new HashMap<>();

    for (int[] pair : prerequisites) {
      preReqMap.putIfAbsent(pair[0], new java.util.ArrayList<>());
      preReqMap.get(pair[0]).add(pair[1]);
    }

    HashSet<Integer> visited = new HashSet<>();

    for (int i = 0; i < numCourses; i++) {
      if (!dfs(preReqMap, visited, i)) {
        return false;
      }
    }

    return true;
  }

  public boolean dfs(HashMap<Integer, List<Integer>> preReq, HashSet<Integer> visited, int root) {
    List<Integer> prerequisites = preReq.get(root);
    if (prerequisites == null || prerequisites.size() == 0) {
      return true;
    }

    visited.add(root);

    for (int prerequisite : prerequisites) {
      if (visited.contains(prerequisite)) {
        return false;
      } else {
        if (!dfs(preReq, visited, prerequisite)) {
          return false;
        }
      }
    }
    visited.remove(root);
    preReq.put(root, null);
    return true;
  }

  public static void test(){
    CourseSchedule courseSchedule = new CourseSchedule();
    int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
    assert (courseSchedule.canFinish(4, prerequisites));
  }
}
