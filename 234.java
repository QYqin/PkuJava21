/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mid(ListNode head){

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
    public ListNode reverse(ListNode head){
        ListNode p = null;

        while(head != null){

            ListNode t = head.next;

            head.next = p;
            p = head;

            head = t;
        }

        return p;
		
	}

	public boolean isPalindrome(ListNode head) {
	    ListNode mm = mid(head );
		ListNode re =reverse(mm);
		while (re!=null&&head!=null){
			if(re.val !=head.val){
				return false;}
			re = re.next;
			head = head.next;
			
		}
		return true;

	}
}