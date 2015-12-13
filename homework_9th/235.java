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
    //     private  int lef;
    //     private  int righ;
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { 
    //     if(root ==p) lef  =1;
    //     if(root ==q) righ =1;
    //     if(root.left ! =null)
    //     lowestCommonAncestor(root.left,p,q);
    //     if(root ==p) lef  =1;
    //     if(root ==q) righ =1;
    //     if(root.right != null)
    //     lowestCommonAncestor(root.right,p,q);
    //     if (righ ==1 &&lef ==1)
    //     return root;
    //     else return null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left,p, q);
        }
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right,p, q);
        }
        
        return root ==p?p:root==q?q:root;
    }
}