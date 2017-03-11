
public class searchInsert {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		searchInsert(new int[]{1,3},0);
	}
	public static int searchInsert(int[] nums,int target){
		int p=0,q=nums.length-1;
		int c=(p+q)/2;
		while(p<q){
			if(nums[c]>target){
				q=c-1;
			}else if(nums[c]<target){
				p=c+1;
			}else{
				break;
			}
			c=(p+q)/2;
		}
		if(nums[c]>=target){
			return c;
		}else{
			return c+1;
		}
	}
}
