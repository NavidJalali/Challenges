package io.navidjalali;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Solved by backtracking.
*/

class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		addCombination(result, "", 0, 0, n);
		return result;
	}

	public void addCombination(List<String> list,
														 String currentCombination,
														 int openCount,
														 int closeCount,
														 int max) {
		if (currentCombination.length() == max * 2)
			list.add(currentCombination);
		if (openCount < max)
			addCombination(list, currentCombination + "(", openCount + 1, closeCount, max);
		if (closeCount < openCount)
			addCombination(list, currentCombination + ")", openCount, closeCount + 1, max);
	}
}
