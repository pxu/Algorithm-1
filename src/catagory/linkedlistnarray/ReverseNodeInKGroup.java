package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/22/17.
 */
//http://www.lintcode.com/en/problem/reverse-nodes-in-k-group/
//http://www.jiuzhang.com/solution/reverse-nodes-in-k-group/

public class ReverseNodeInKGroup {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastNode = dummyHead;
        while(lastNode != null) {
            lastNode = reverse(lastNode, k);
        }
        return dummyHead.next;
    }

    //如果可以反转，就反转，不然return null, return 反转后最后一个node
    private ListNode reverse(ListNode dummyHead, int k) {
        ListNode current = dummyHead.next;
        for (int i = 0; i < k; i++) { //先计算到没到k个
            if(current == null) {
                return null;
            }
            current = current.next;
        }
        ListNode end = current;
        ListNode firstNode = dummyHead.next;// 使用dummy node，
        ListNode prev = null;
        current = firstNode;

        while (current != end) { //想下反转的过程
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        dummyHead.next = prev;
        firstNode.next = current;
        return firstNode;
    }
}
