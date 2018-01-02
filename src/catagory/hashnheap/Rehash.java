package catagory.hashnheap;

/**
 * http://www.lintcode.com/en/problem/rehashing/
 * https://www.jiuzhang.com/solution/rehashing/
 *   //a % b = (a % b + b) % b
 */
public class Rehash {
   class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode[] rehashing(ListNode[] hashTable) {
     int destSz = hashTable.length * 2;
     ListNode[] result = new ListNode[destSz];

     for (int srcIdx = 0; srcIdx < hashTable.length; srcIdx++) {
       ListNode srcCrt = hashTable[srcIdx];
       while (srcCrt != null) {
         ListNode destTmp = new ListNode(srcCrt.val);
         int destIdx = srcCrt.val >= 0 ?
                        srcCrt.val % destSz
                        : (srcCrt.val % destSz + destSz) % destSz;
         if (result[destIdx] == null) {
           result[destIdx] = destTmp;
         } else {
           ListNode destCrt = result[destIdx];
           while (destCrt.next != null) {
             destCrt = destCrt.next;
           }
           destCrt.next = destTmp;
         }

         srcCrt = srcCrt.next;
       }

     }
     return result;
  }

}
