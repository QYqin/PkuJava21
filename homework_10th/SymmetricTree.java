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
		if(root==null){
			return true;
		}
		return check(root.left,root.right); 
    }
	public boolean check(TreeNode root1,TreeNode root2){
		if(root1==null&&root2==null){
			return true;
		}else if(root1==null||root2==null){
			return false;
		}else if(root1.val!=root2.val){
			return false;
		}
		return check(root1.left,root2.right)&&check(root1.right,root2.left);
	}
}