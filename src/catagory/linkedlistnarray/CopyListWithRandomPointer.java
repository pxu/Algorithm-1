package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/23/17.
 */
//https://leetcode.com/problems/copy-list-with-random-pointer/description/
public class CopyListWithRandomPointer {
    static class RandomListNode {
        public int value;
        public RandomListNode next;
        public RandomListNode random;
        public RandomListNode(int value, RandomListNode next, RandomListNode random) {
            this.value = value;
            this.next = next;
            this.random = random;
        }
    }
    public static void main(String[] args) {

    }
    public static RandomListNode copyList(RandomListNode head) {
        cloneNode(head);
        setRandom(head);
        return recoverLinkedList(head);
    }
    public static void cloneNode(RandomListNode head) {
        RandomListNode current = head;
        while (current != null) {
            RandomListNode next = current.next;
            RandomListNode clone = new RandomListNode(current.value, current.next, null);
            current.next = clone;
            current = next;
        }
    }
    public static void setRandom(RandomListNode head) {

        RandomListNode currentSrc = head;
        while (currentSrc != null) {
            RandomListNode currentDest = currentSrc.next;
            if (currentSrc.random != null) {
                currentDest.random = currentSrc.random.next;
            }
            currentSrc = currentDest.next;
        }
    }
    public static RandomListNode recoverLinkedList(RandomListNode head) {

            RandomListNode destHead = head.next;
            while(head != null) {
                RandomListNode dest = head.next;
                head.next = dest.next;
                if(dest.next != null) {//只要确保 下一个src 不是null 就可以了    src -> dest -> nextsrc -> nextDest
                    dest.next = dest.next.next;
                }
                head = head.next;
            }
            return destHead;

    }
}
