package io.navidjalali;

import io.navidjalali.datastructures.BinaryTreeNode;

/*
Given the root of a binary tree, invert the tree, and return its root.
*/

public class InvertBinaryTree {
	public BinaryTreeNode invertTree(BinaryTreeNode root) {
		if (root == null) return null;
		BinaryTreeNode temp = invertTree(root.left);
		root.left = invertTree(root.right);
		root.right = temp;
		return root;
	}
}
