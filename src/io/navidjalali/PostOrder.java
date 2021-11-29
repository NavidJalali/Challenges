package io.navidjalali;

import io.navidjalali.datastructures.NAryTreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
Given the root of an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples).

        1
    /   |   \
   3    2    4
 /  \
5    6

Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]
*/

public class PostOrder {
	public List<Integer> postorder(NAryTreeNode root) {
		if (root == null) return new ArrayList<>();
		List<Integer> result =
			root
				.children
				.stream()
				.map(node -> postorder(node))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		result.add(root.val);

		return result;
	}
}
