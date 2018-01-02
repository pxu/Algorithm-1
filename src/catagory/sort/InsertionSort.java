package catagory.sort;

/**
 * Created by zhahua on 12/4/17.
 */
public class InsertionSort {
  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }
  public static void main(String[] args) {
    int[] values = new int[] {1,3,5,2,4,6};
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;

    for(int v : values) {
      prev.next = new ListNode(v);
      prev = prev.next;
    }
    new InsertionSort().insertionSortList(dummy.next);
  }
  public ListNode insertionSortList(ListNode head) {
    ListNode dummy = new ListNode(0);// 生成另一个链表

    ListNode curt = head;

    while (curt != null) {
      ListNode node = dummy;
      while (node.next != null && node.next.val < curt.val) {//因为要插入，所以循环前一个元素
        node = node.next;
      }
      ListNode next = curt.next;
      curt.next = node.next;
      node.next = curt;
      curt = next;
    }
    return dummy.next;
  }
}
