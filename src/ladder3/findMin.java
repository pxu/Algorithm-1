package ladder3;

/**
 * Created by zhahua on 2/5/17.
 */
public class findMin {
    public static void  main(String[] args){
        //{4, 5, 6, 7, 8,9,10,11,0, 1, 2}
        System.out.println(new Solution().findMin(new int[]{6,1,2,3,4,5}));
    }
}
class Solution {

    public int detectPhase(int [] nums,int mid,boolean asc){
        int max =Math.max(nums[0],nums[nums.length-1]);
        int min =Math.min(nums[0],nums[nums.length-1]);

        if(nums[mid]>=max&&nums[mid+1]>=max)
            return asc?1:-1;
        if(nums[mid]<=min&&nums[mid+1]<=min)
            return asc?-1:1;
        return 0;
    }
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if(nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.min(nums[0],nums[1]);
        if(nums[nums.length/2]<Math.max(nums[0],nums[nums.length-1])
            && nums[nums.length/2]>Math.min(nums[0],nums[nums.length-1])){
            return Math.min(nums[0],nums[nums.length-1]);
        }
        boolean isAsc=nums[0]>nums[nums.length-1];
        int low=0;
        int high=nums.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            int phase=detectPhase(nums,mid,isAsc);
            System.out.println(""+nums[mid]+","+phase);
            if(phase==1)
                low=mid+1;
            else if(phase==-1)
                high=mid-1;
            else
                return isAsc?nums[mid+1]:nums[mid];
        }
        return 0;
    }
}