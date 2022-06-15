package io.navidjalali;

import io.navidjalali.datastructures.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeOrderLevelTraversal {
  public List<List<Integer>> levelOrder(BinaryTreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, root, 0);
    return result;
  }

  public void dfs(List<List<Integer>> result, BinaryTreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (result.size() < depth + 1) {
      result.add(new ArrayList<>());
      dfs(result, node, depth);
      return;
    }

    result.get(depth).add(node.val);
    dfs(result, node.left, depth + 1);
    dfs(result, node.right, depth + 1);
  }
}
