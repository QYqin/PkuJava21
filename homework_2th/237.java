/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
public void deleteNode(ListNode node) {
	    ListNode pp=node.next;
	    ListNode p =node;
	    while(pp!=null){
	    	node.val =pp.val;
	    	p =node;
	    	node =pp;
	    	pp =pp.next;
	    }
	    p.next =null;
	 }
}