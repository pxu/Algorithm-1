import java.util.Arrays;
import java.util.List;


public class ThreeSumCloset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threeSumClosest(new int[]{-1, 2, 1, -4},0);
	}
	public static int twoSumCloset(int[] num){
		int i=0;
		int j=num.length-1;
		int target=100;
		int min=Math.abs(num[i]+num[j]-target);
		while(i<j){
			int sum=num[i]+num[j];
			if(sum>target){
				if(Math.abs(sum-target)<min){
					min=Math.abs(sum-target);
					System.out.println(String.format("i=%d,j=%d",num[i],num[j]));
				}
				j--;
			}else if(sum<target){
				if(Math.abs(sum-target)<min){
					min=Math.abs(sum-target);
					System.out.println(String.format("i=%d,j=%d",num[i],num[j]));
				}
				i++;
			}else{
				System.out.print("what?");
				break;
			}
		}
		return min;
	}
	public static int threeSumClosest(int[] num, int targetAll) {
        Arrays.sort(num);
		int min=Integer.MAX_VALUE;
		int result=0;
		for(int k=0;k<num.length-2;k++){
			int i=k+1;
			int j=num.length-1;
			int target=targetAll-num[k];
			while(i<j){
				int sum=num[i]+num[j];
				if(sum>target){
					if(Math.abs(sum-target)<min){
						min=Math.abs(sum-target);
						result=sum+num[k];
						//System.out.println(String.format("k=%d,i=%d,j=%d",num[k],num[i],num[j]));
					}
					j--;
				}else if(sum<target){
					if(Math.abs(sum-target)<min){
						min=Math.abs(sum-target);
						result=sum+num[k];
						//System.out.println(String.format("k=%d,i=%d,j=%d",num[k],num[i],num[j]));
					}
					i++;
				}else{
					return targetAll;
				}
			}
		}
		return result;
    }

}
