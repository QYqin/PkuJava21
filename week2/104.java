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
    public int maxDepth(TreeNode root) {
       int lchilddep,rchilddep;
	        //int dep=0;
	        if(root==null)
	        {
	            return 0;
	        }
	        else{
	            lchilddep=maxDepth(root.left);
	            rchilddep=maxDepth(root.right);
	            return(lchilddep>rchilddep)?(lchilddep+1):(rchilddep+1);
	        }
        
    }
}