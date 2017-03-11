package ladder3;

/**
 * Created by zhahua on 2/6/17.
 */
public class search {
    public static void main(String[] args){
        new Solution4().search(new int[]{4, 5,6,7,8,9, 1, 2, 3},2);
    }
}
class Solution4 {
    /**
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        int low=0;
        int high=A.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(A[mid]>target){
                if(A[mid]>A[high]&&target<=A[high])
                    low=mid+1;
                else
                    high=mid-1;
            }else if(A[mid]<target){
                if(A[mid]>A[high]&&target>A[high]
                        ||A[mid]<=A[high]&&target<=A[high])
                    low=mid+1;
                else
                    high=mid-1;
            }else
                return mid;
        }
        return -1;
    }
}
