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
	    public int minDepth(TreeNode root) {
	        if(root==null) return 0;
	        int t1=minDepth(root.left);
	        int t2=minDepth(root.right);
	        if(t1==0) return t2+1;
	        if(t2==0) return t1+1;
	        return(Math.min(t1,t2)+1);

	    }
}