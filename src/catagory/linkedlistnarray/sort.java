package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/24/17.
 */
// @see <a href="https://leetcode.com/problems/sort-list/description/"> link </a>
public class sort {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    public static void main(String[] args) {
        ListNode n4 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n3.next = n4;
        ListNode n2 = new ListNode(3);
        n2.next = n3;
        ListNode n1 = new ListNode(1);
        n1.next = n2;
        ListNode re = new sort().mergeSort(n1);
    }
        /**
         * @param head: The head of linked list.
         * @return: You should return the head of the sorted linked list,
        using constant space complexity.
         */
        public ListNode sortList(ListNode head) {
            return mergeSort(head);
        }

        private ListNode mergeSort(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }
            ListNode mid = splitList(head);
            head = mergeSort(head);
            mid = mergeSort(mid);
            return mergeList(head, mid);

        }

        private ListNode splitList(ListNode head) {// 背一下 2个node 3 个node 的情况
            //这个函数必须保证这个链表可以被拆成两个更小的链表。
            //不然会stack overflow
            if(head == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null) {
                fast = fast.next;
                if(fast != null) {
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            ListNode result = slow.next;
            slow.next = null;
            return result;
        }

        private ListNode mergeList(ListNode head, ListNode mid) {
            ListNode dummy = new ListNode(0);
            ListNode resultPrev = dummy;
            ListNode i = head;
            ListNode j = mid;

            while (i != null && j != null) {
                if (i.val <= j.val) {
                    resultPrev.next = i;
                    resultPrev = i; //* don't forget to add this line
                    i = i.next;
                } else {
                    resultPrev.next = j;
                    resultPrev = j;
                    j = j.next;
                }
            }
            if (i != null) {
                resultPrev.next = i; //* while not required
            }
            if (j != null) {
                resultPrev.next = j;
            }
            return dummy.next;
        }

    }

