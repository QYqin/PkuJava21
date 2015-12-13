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
        return depth(root)!=-1;
	}
	public int depth(TreeNode root1){
		if(root1==null){
			return 0;
		}
		int depthleft=depth(root1.left);
        int depthright=depth(root1.right);
        if(depthleft==-1||depthright==-1||Math.abs(depthright-depthleft)>1){
        	return -1;
        }
		return Math.max(depthleft, depthright)+1;
    }
}
/*int lengthshort=0;
        int lengthlang=0;
        lengthlang=checkl(root.left,root.right);
        lengthshort	=checks(root.left,root.right);
        if (lengthlang>lengthshort+1){
        	return false;
        }
        return true;
    }
	public int checkl(TreeNode root1,TreeNode root2){
		int i=0,j=0;
		if(root1==null&&root2==null){
			return 0;
		}else if(root1==null){
			return 1+checkl(root2.left,root2.right);
		}else if(root2==null){
			return 1+checkl(root1.left,root1.right);
		}else{
			i=checkl(root1.left,root1.right)+1;
			j=checkl(root2.left,root2.right)+1;
			return Math.max(i, j);
		}
	}
	public int checks(TreeNode root1,TreeNode root2){
		int i=0,j=0;
		if(root1!=null&&root2!=null){
			i=1+checks(root1.left,root1.right);
			j=1+checks(root2.left,root2.right);
			return Math.min(i, j);
		}else{
			return 0;
		}
	}*/
