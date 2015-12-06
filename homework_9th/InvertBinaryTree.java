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
    public TreeNode invertTree(TreeNode root) {
       /*//TreeNode temp =new TreeNode(0);
		Stack<TreeNode> tree_stack = new Stack<TreeNode>(); 
		 if(root==null){
	            return root;
	        }
		 //非递归算法
		 tree_stack.push(root);
		 while(tree_stack.size()>0){
			 TreeNode temp =tree_stack.pop();
			 TreeNode tree_left=temp.left;
			 temp.left=temp.right;
			 temp.right=null;
			 if(temp.left!=null){
				 tree_stack.push(tree_left);
			 }
			 if(temp.right!=null){
				 tree_stack.push(temp.right);
			 }
		 }
		/*while(root.left!=null||root.right!=null){
			temp=root.left;
			root.left=root.right;
			root.right=temp;
		}*/
		/*return root;*/
		//		TreeNode temp =new TreeNode(0);
		Stack<TreeNode> tree_stack = new Stack<TreeNode>(); 
		 if(root==null){
	            return root;
	        }
		 //非递归算法
		 tree_stack.push(root);
		 while(tree_stack.size()>0){
			 TreeNode temp =tree_stack.pop();
			 TreeNode tree_left=null;
			 TreeNode tree_right=null;
//			 temp.left=temp.right;
//			 temp.right=tree_left;
			 if(temp.left!=null){
				 tree_left=temp.left;
				 tree_stack.push(tree_left);
			 }
			 if(temp.right!=null){
				 tree_right=temp.right;
				 tree_stack.push(temp.right);
			 }
			 temp.right=tree_left;
			 temp.left=tree_right;
		 }
		/*while(root.left!=null||root.right!=null){
			temp=root.left;
			root.left=root.right;
			root.right=temp;
		}*/
		return root;
		/*递归方法
		if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
		*/
    }
}