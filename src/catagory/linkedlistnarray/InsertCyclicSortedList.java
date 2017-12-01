package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/26/17.
 */
public class InsertCyclicSortedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode insert(ListNode node, int x) {
        // write your code here
        if(node == null) {
            ListNode tmp = new ListNode(x);
            tmp.next = tmp;
            return tmp;
        }

        ListNode current = node.next;
        ListNode prev = node;
        //三种情况， 1->1->1    5->(**)->1->3->4    一般情况
        do{
            if (prev.val > current.val && (prev.val <= x || current.val >= x)) {
                break;
            }
            if(current.val >= x && prev.val <= x) {
                break;
            }
            prev = current;
            current = current.next;
        } while (current != node.next); //用do while


        ListNode tmp = new ListNode(x);
        tmp.next = prev.next;
        prev.next = tmp;
        return tmp;
    }
}
