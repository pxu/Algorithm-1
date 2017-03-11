package ladder3;

/**
 * Created by zhahua on 2/6/17.
 */
public class findFirstBadVersion {
    public static void main(String[] args){
        new Solution3().findFirstBadVersion(30);
    }
}
class SVNRepo {
    public static boolean isBadVersion(int k){
        return k>=10;
    }
}
/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether
 * the kth code version is bad or not.
 */
class Solution3 {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int low=1;
        int high=n;
        while(low<high){
            int mid=(low+high)/2;
            System.out.println(mid);
            if(SVNRepo.isBadVersion(mid)){
                high=mid;
            }else
                low=mid+1;
        }
        //System.out.println(high);
        return high;
    }
}
