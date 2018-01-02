package catagory.linkedlistnarray;

/**
 * Created by zhahua on 11/26/17.
 */
//九章第6节
public class findMedian {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (findK(A, B, 0, 0, len / 2) + findK(A, B, 0, 0, len / 2 + 1))
                    / 2.0; // 2.0 not 2
        }
        return findK(A, B, 0, 0, len / 2 + 1);
    }
    //k 代表第几个数
    public int findK(int[] A, int[] B, int startA, int startB, int k) {
        if (startA == A.length) {
            return B[startB + k - 1];
        }
        if (startB == B.length) {
            return A[startA + k - 1];
        }
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }
        //每次比较两个数组中第k / 2 个数，然后删除小的那个（包含）。 可以证明出第k个数还在
        int valueA = startA + k / 2 - 1 >= A.length ?
                Integer.MAX_VALUE : A[startA + k / 2 - 1];
        int valueB = startB + k / 2 - 1 >= B.length ?
                Integer.MAX_VALUE : B[startB + k / 2 - 1];

        if (valueA < valueB) {
            return findK(A, B, startA + k / 2, startB, k - k / 2); // not k / 2
        } else {
            return findK(A, B, startA , startB + k / 2, k - k / 2);
        }
    }
    public static void main(String[] args) {
        double result = new findMedian().findMedianSortedArrays(new int[]{1,2,3,4,5,6}, new int[]{2,3,4,5});
        return;

    }


}
