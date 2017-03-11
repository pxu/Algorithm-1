
public class SwapNodes {

	/**
	 * Definition for singly-linked list.
	 */ public static class ListNode {
	      int val;
	      public ListNode next;
	      public ListNode(int x) { val = x; }
	  }
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []arr=new int[]{1,2,3,4,5,6};
		ListNode next=null;
		ListNode head=null;
		for(int i=arr.length-1;i>=0;i--){
			ListNode current=new ListNode(arr[i]);
			current.next=next;
			if(i==0){
				head=current;
				break;
			}
			next=current;
		}
		
		ListNode current=swapPairs(head);;
		while(current!=null){
			System.out.print(current.val+" ");
			current=current.next;
		}
		
	}
    public static ListNode  swapPairs(ListNode head) {
        ListNode prev=null;
        if(head==null)
        	return null;
        ListNode current1=head;
        if(head.next==null)
        	return head;
        ListNode current2=null;
        ListNode next=null;
        
        while(current1!=null){
        	if(current1.next==null){
        		break;
        	}
        	current2=current1.next;
        	next=current2.next;
        	if(prev==null){
        		head=current2;
        	}else{
        		prev.next=current2;
        	}
        	current1.next=next;
    		current2.next=current1;
    		prev=current1;
    		current1=next;
        }
        return head;
        
    }
}
