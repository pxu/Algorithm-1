package ladder3;

/**
 * Created by zhahua on 2/6/17.
 */
public class findPeak {
    public static  void main(String[] args){
        new Solution1().findPeak(new int[]{1, 2, 1, 3, 4, 5, 7, 6});
    }
}
class Solution1 {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if(A.length<3)
            return -1;
        if(A.length==3)
            return 1;
        int low=0;
        int high=A.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(A[mid]<A[mid+1])
                low=mid+1;
            else if(A[mid]>A[mid+1]){
                if(A[mid-1]<A[mid])
                    return mid;
                else
                    high=mid-1;
            }
        }
        return -1;
    }
}
