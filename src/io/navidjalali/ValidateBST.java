package io.navidjalali;

import io.navidjalali.datastructures.BinaryTreeNode;

public class ValidateBST {
  public boolean isValidBST(BinaryTreeNode root) {
    return dfs(root, null, null);
  }

  boolean dfs(BinaryTreeNode node, Integer max, Integer min) {
    if (node == null)
      return true;

    if ((min != null && node.val < min) || (max != null && node.val > max))
      return false;

    return dfs(node.left, node.val, min) && dfs(node.right, max, node.val);
  }
}
