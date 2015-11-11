/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ln = head;
        if(ln == null)
        	return null;
        while(ln.next!=null){
        	if(ln.val==ln.next.val){
        		ln.next=ln.next.next;
        	}else
        		ln = ln.next;
        }
        //return ln;
        return head;
    }
}