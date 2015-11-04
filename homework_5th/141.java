/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head==null||head.next==null) return false;
        ListNode first =head.next;
        ListNode second = head.next.next;
        while (first !=second){
            if (second==null||first ==null) return false;
            if(first.next ==null) return false;
            first = first.next;
            if(second.next ==null||second.next.next==null) return false;
            second = second.next.next;

        }
        return true;
    }
}