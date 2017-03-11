import java.util.Arrays;


public class nextPermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nextPermutation(new int[]{2,3,1});
	}
	public static void nextPermutation(int[] nums){
		if(nums.length<2)
			return;
		int boundary=0;
		int i;
		int q=0;
		for(i=nums.length-1;i>0;i--){		
			if(nums[i-1]<nums[i]){
				q=nums[i-1];
				break;
			}
		}
		boundary=i;
		if(boundary!=0){
			
			int j;
			for(j=nums.length-1;j>=boundary;j--){
				if(nums[j]>q){
					int tmp=nums[boundary-1];
					nums[boundary-1]=nums[j];
					nums[j]=tmp;
					break;
				}
			}
			if(j==boundary-1)
				nums[boundary]=q;
			
		}
		for(int k=boundary,m=nums.length-1;k<m;k++,m--){
			int tmp=nums[k];
			nums[k]=nums[m];
			nums[m]=tmp;
		}
		System.out.println(Arrays.toString(nums));
	}
}
