import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FourSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//threeSum(new int[]{7,5,-8,-6,-13,7,10,1,1,-4,-14,0,-1,-10,1,-13,-4,6,-11,8,-6,0,0,-5,0,11,-9,8,2,-6,4,-14,6,4,-5,0,-12,12,-13,5,-6,10,-10,0,7,-2,-5,-12,12,-9,12,-9,6,-11,1,14,8,-1,7,-13,8,-11,-11,0,0,-1,-15,3,-11,9,-7,-10,4,-2,5,-4,12,7,-8,9,14,-11,7,5,-15,-15,-4,0,0,-11,3,-15,-15,7,0,0,13,-7,-12,9,9,-3,14,-1,2,5,2,-9,-3,1,7,-12,-3,-1,1,-2,0,12,5,7,8,-7,7,8,7,-15});
		fourSum(new int[]{7,5,-8,-6,-13,7,10,1,1,-4,-14,0,-1,-10,1,-13,-4,6,-11,8,-6,0,0,-5,0,11,-9,8,2,-6,4,-14,6,4,-5,0,-12,12,-13,5,-6,10,-10,0,7,-2,-5,-12,12,-9,12,-9,6,-11,1,14,8,-1,7,-13,8,-11,-11,0,0,-1,-15,3,-11,9,-7,-10,4,-2,5,-4,12,7,-8,9,14,-11,7,5,-15,-15,-4,0,0,-11,3,-15,-15,7,0,0,13,-7,-12,9,9,-3,14,-1,2,5,2,-9,-3,1,7,-12,-3,-1,1,-2,0,12,5,7,8,-7,7,8,7,-15},0);
		
	}
	public static void TwoSum(){
		// TODO Auto-generated method stub
		int [] arr=new int[]{1,5,6,30,40,44,56,69,70,93,95,97};
		Arrays.sort(arr);
		
		int i=0,j=arr.length-1;
		int target=100;
		while(i<j){
			int sum=arr[i]+arr[j];
			if(sum<target){
				i++;
			}else if(sum>target){
				j--;
			}else{
				System.out.println(String.format("i=%d,j=%d, sum=%d", i,j,arr[i]+arr[j]));
				i++;j--;
			}
		}
	}
	public static List<List<Integer>> threeSum_online(int[] nums) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	 
	    if(nums == null || nums.length<3)
	        return result;
	 
	    Arrays.sort(nums);
	 
	    for(int i=0; i<nums.length-2; i++){
	        if(i==0 || nums[i] > nums[i-1]){
	            int j=i+1;
	            int k=nums.length-1;
	 
	            while(j<k){
	                if(nums[i]+nums[j]+nums[k]==0){
	                    List<Integer> l = new ArrayList<Integer>();
	                    l.add(nums[i]);
	                    l.add(nums[j]);
	                    l.add(nums[k]);
	                    result.add(l);
	 
	                    j++;
	                    k--;
	 
	                    //handle duplicate here
	                    while(j<k && nums[j]==nums[j-1])
	                        j++;
	                    while(j<k && nums[k]==nums[k+1])
	                        k--;
	 
	                }else if(nums[i]+nums[j]+nums[k]<0){
	                    j++;
	                }else{
	                    k--;
	                }
	            }
	        }
	 
	    }
	 
	    return result;
	}
	public static List<List<Integer>> threeSum(int[] arr) {
        Arrays.sort(arr);
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		int target=0;
		for(int k=0;k<arr.length-2;k++){
			if(k==0 || arr[k-1]<arr[k]){
				int targetSub=target-arr[k];
				int i=k+1,j=arr.length-1;
				while(i<j){
					int sum=arr[i]+arr[j];
					if(sum<targetSub){
						i++;
					}else if(sum>targetSub){
						j--;
					}else{
						System.out.println(String.format("k=%d,i=%d,j=%d, sum=%d",arr[k],arr[i],arr[j],arr[k]+arr[i]+arr[j]));
						List<Integer> tuple=new ArrayList<Integer>();
						tuple.add(arr[k]);tuple.add(arr[i]);tuple.add(arr[j]);
						result.add(tuple);
						int oldValue=arr[i];
						for(;i<j&&arr[i]==oldValue;i++);
						oldValue=arr[j];
						for(;i<j&&arr[j]==oldValue;j--);
						
					}
				}
			}
		}
		return result;
	}
	public static List<List<Integer>> fourSum(int[] arr,int target){
		List<List<Integer>> result=new ArrayList<List<Integer>>();
		Arrays.sort(arr);
		for(int m=0;m<arr.length-3;m++){
			if(m!=0&&arr[m-1]==arr[m])
				continue;
			for(int k=m+1;k<arr.length-2;k++){
				if(k!=m+1&&arr[k-1]==arr[k])
					continue;
				int sub_target=target-arr[m]-arr[k];
				int i=k+1;
				int j=arr.length-1;
				while(i<j){
					if(i!=k+1&&arr[i-1]==arr[i]){
						i++;continue;
					}
					if(j!=arr.length-1&&arr[j+1]==arr[j]){
						j--;continue;
					}
					int sub_sum=arr[i]+arr[j];
					if(sub_sum>sub_target){
						j--;
					}else if(sub_sum<sub_target){
						i++;
					}else{
						List<Integer> quadruplets=new ArrayList<Integer>();
						quadruplets.add(arr[m]);
						quadruplets.add(arr[k]);
						quadruplets.add(arr[j]);
						quadruplets.add(arr[i]);
						result.add(quadruplets);
						i++;j--;
					}
				}
			}
		}
		return result;
	}
}
