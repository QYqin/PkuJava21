import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class Solution {
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		Stack<TreeNode> tns = new Stack<TreeNode>();
		tns.push(root);
		while (!tns.isEmpty()) {
			TreeNode cur = tns.pop();
			TreeNode tem = cur.left;
			cur.left = cur.right;
			cur.right = tem;
			if (cur.left != null)
				tns.push(cur.left);
			if (cur.right != null)
				tns.push(cur.right);
		}
		return root;
	}
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}
