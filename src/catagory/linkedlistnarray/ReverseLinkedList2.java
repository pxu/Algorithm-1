package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/26/17.
 */
public class ReverseLinkedList2 {
    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        if (head == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode c = dummy;//从dummy开始，想下
        for (int i = 1; i < m; i++) { //得到要reverse的第一个数的前一个
            c = c.next;
        }

        ListNode revDummy = c;//要reverse的第一个数的前一个
        ListNode first = revDummy.next;    //x1 x2 x3 o1 o2 o3 x4  //x3 -> o3   o1 -> x4  所以要把o1暂存起来
        ListNode p = null;
        c = revDummy.next;
        int j = 0;
        while (j < (n - m + 1)) {
            ListNode next = c.next;
            c.next = p;
            p = c;
            c = next;
            j++;
        }
        revDummy.next = p;
        first.next = c;
        return dummy.next;
    }
}
