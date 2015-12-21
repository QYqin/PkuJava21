/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        // if(root.left==null&&root.right!=null)
        // return false;
        // if(root.right==null&&root.left!=null)
        // return false;
        // if(root.right==null&&root.left==null)
        // return true;
        // TreeNode p=root.left;
        // TreeNode q=root.right;
        // if (p.val!=q.val)
        // return false;
        // isSymmetric(root.left);
        // isSymmetric(root.right);
        // return true;
        if (root == null)
			return true;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root.left);
		s2.push(root.right);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode n1 = s1.pop();
			TreeNode n2 = s2.pop();
			if (n1 == null && n2 == null) {
				continue;
			} else if (n1 == null || n2 == null) {
				return false;
			} else if (n1.val != n2.val) {
				return false;
			} else {
				s1.push(n1.left);
				s1.push(n1.right);
				s2.push(n2.right);
				s2.push(n2.left);
			}
		}
		return true;
    }
}