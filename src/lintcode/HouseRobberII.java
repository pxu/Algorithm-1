package lintcode;

import java.util.Arrays;

public class HouseRobberII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long re=houseRobber(new int[]{1,1,5,3,6});
		System.out.println(re);
	}
	public static long max(long[] arr){
		long maxNum=arr[0];
		for(int i=1;i<arr.length;i++){
			maxNum=arr[i]>maxNum? arr[i]:maxNum;
		}
		return maxNum;
	}
	public static long houseRobber(int[] A) {
	    // write your code here
		if(A.length==0)
			return 0;
		if(A.length==1)
			return A[0];
		if(A.length==2)
			return max(new long[]{A[0],A[1]});
		long[][] dp=new long[2][4];
		dp[0][0]=A[1];
		dp[0][1]=A[0];
		dp[0][2]=A[2];
		dp[0][3]=A[0]+A[2];
		for (int i=3;i<A.length;i++){
			dp[1][0]=max(new long[]{dp[0][0],dp[0][2]});
			dp[1][1]=max(new long[]{dp[0][1],dp[0][3]});
			dp[1][2]=dp[0][0]+A[i];
			dp[1][3]=dp[0][1]+A[i];
			System.out.println(Arrays.toString( dp[1]));
			dp[0][0]=dp[1][0];
			dp[0][1]=dp[1][1];
			dp[0][2]=dp[1][2];
			dp[0][3]=dp[1][3];
		}
		return max(new long[]{dp[0][0],dp[0][2],dp[0][1]});
	}
}
