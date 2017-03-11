package ladder2;

public class partitionArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Solution().partitionArray(new int[]{1,5,0,3,2,2,4,1}, 2);
	}

}
class Solution {
	/** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
	    //write your code here
    	if(nums.length==0)
    		return 0;
    	int i=0;
    	int j=nums.length-1;
    	while(true){
    		while(j>i && nums[j]>=k){
    			j--;
    		}
    		if(j==i)
    			break;
    		while(j>i && nums[i]<k){
    			i++;
    		}
    		if(j==i)
    			break;
    		int tmp=nums[j];
    		nums[j]=nums[i];
    		nums[i]=tmp;
    	}
    	return nums[i]<k? i+1:i;
    }
}