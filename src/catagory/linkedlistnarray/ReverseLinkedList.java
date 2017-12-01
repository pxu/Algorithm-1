package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/22/17.
 */
public class ReverseLinkedList {
    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
    public static void main(String[] main) {
        ListNode n4 = new ListNode(4, null);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
       ListNode nh;// = reverseLinkedList(n1);
        n2.next = null;
        nh = reverseLinkedList(n1);
        n1.next = null;
        nh = reverseLinkedList(n1);
        nh = reverseLinkedList(null);

    }
    private static ListNode reverseLinkedList(ListNode head) {

            // write your code here
            ListNode prev = null;
            ListNode current = head;

            while(current != null) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;

    }

//    public ListNode reverse(ListNode head) {
//        ListNode prev = null;
//        while (head != null) {
//            ListNode temp = head.next;
//            head.next = prev;
//            prev = head;
//            head = temp;
//        }
//        return prev;
//    }
}
