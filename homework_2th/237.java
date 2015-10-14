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
//	        while(node!=null)
//	        {
//	            if(node.val==)
//	            {
//	                node
//	            }
//	        }
		 if(node==null||node.next==null)
		 {
			 return ;
		 }
		 //ListNode tmp;
		 //tmp=node.next;
		 //因为没有头结点，所以通过将下一个节点赋值给上一个节点，再删除下一个节点的方法回收内存。
		 node.val=node.next.val;
		 //ListNode *temp=node.next;
		 node.next=node.next.next;
		 //delete temp;
		 //java不需要显性的回收内存，JVM的垃圾回收机制会自动的回收不需要的内存。
    }
}