package ladder3;

/**
 * Created by zhahua on 2/6/17.
 */
public class searchRange {
    public static void main(String[] args){
        new Solution5().searchRange(new int[]{1,2,3,4,5,5,5,5,5,8},0);
    }
}
class Solution5 {
    /**
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {

        short[] map=new short[0xFFFF];
        int x=-1;
        short index=(short)(x & 0xFFFF);
        short wt=map[index];

        // write your code here
        if(A.length==0)
            return new int[]{-1,-1};
        int low=0;
        int high=A.length-1;
        while(low<high){
            int mid=(low+high)/2;
            if(A[mid]<target)
                low=mid+1;
            else if(A[mid]>=target)
                high=mid;
        }
        if(A[high]!=target)
            return new int[]{-1,-1};
        int[] result=new int[]{high,0};
        low=0;
        high=A.length-1;
        while(low<high){
            int mid=(low+high+1)/2;
            if(A[mid]<=target)
                low=mid;
            else if(A[mid]>target)
                high=mid-1;
        }
        result[1]=high;
        return result;
    }
}
