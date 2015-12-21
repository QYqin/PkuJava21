import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Solution {
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		// if(root.left==null&&root.right==null)
		// return true;
		Stack<TreeNode> leftTree = new Stack<TreeNode>();
		Stack<TreeNode> rightTree = new Stack<TreeNode>();
		leftTree.push(root.left);
		rightTree.push(root.right);
		while (leftTree.size() > 0 && rightTree.size() > 0) {
			TreeNode left = leftTree.pop();
			TreeNode right = rightTree.pop();
			if (left == null && right == null)
				return true;
			if (left == null || right == null)
				return false;
			if (left.val == right.val) {
				leftTree.push(left.right);
				leftTree.push(left.left);
				rightTree.push(right.left);
				rightTree.push(right.right);
			} else {
				return false;
			}
		}
		return true;
	}
}