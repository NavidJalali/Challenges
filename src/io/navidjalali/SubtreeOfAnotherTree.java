package io.navidjalali;

import io.navidjalali.datastructures.BinaryTreeNode;

/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with
the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree T is a tree that consists of a node in T and all of this node's descendants.
The tree T could also be considered as a subtree of itself.
*/

public class SubtreeOfAnotherTree {
  public boolean treeEquals(BinaryTreeNode left, BinaryTreeNode right) {
    if (left == null || right == null) {
      return left == right;
    }

    return left.val == right.val &&
        treeEquals(left.left, right.left) &&
        treeEquals(left.right, right.right);
  }

  public boolean isSubtree(BinaryTreeNode root, BinaryTreeNode subRoot) {
    if(root == null) return subRoot == null;
    if (subRoot == null) return true;
    return treeEquals(root, subRoot) ||
        isSubtree(root.left, subRoot) ||
        isSubtree(root.right, subRoot);
  }
}
