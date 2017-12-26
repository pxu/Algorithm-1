package catagory.linkedlistnarray;

/**
 * https://leetcode.com/problems/partition-list/description/
 * parition list 链表
 */
class PartitionList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode lh = new ListNode(0);
        ListNode lprev = lh;
        ListNode gh = new ListNode(0);
        ListNode gprev = gh;
        
        while (head != null) {
            if (head.val < x) {
                lprev.next = head;
                lprev = head;
            } else {
                gprev.next = head;
                gprev = head;
            }
            head = head.next;
        }
        lprev.next = gh.next;
        gprev.next = null;
        return lh.next;
    }
}