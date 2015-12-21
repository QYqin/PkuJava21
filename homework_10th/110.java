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
    public boolean isBalanced(TreeNode root) {
       return length(root)>=0?true:false;
    }
    public int length(TreeNode p ){
        if (p ==null)
        return 0;
        else{
            int lengthleft= length(p.left);
            int lengthright=length(p.right);
            if(lengthleft<0||lengthright<0||Math.abs(lengthleft-lengthright)>1){
                return -1;
            }
            return Math.max(lengthleft,lengthright)+1;
        }
    }
}