package lintcode;

import java.util.Arrays;

/**
 * Created by zhahua on 1/29/17.
 */
public class BurstBalloons {
    public static void main(String[] args){
//        maxCoins(new int[]{4, 1, 5, 10});
        System.out.println(myMaxCoin(new int[]{4, 1, 5, 10}));
        System.out.println(maxCoins(new int[]{4, 1, 5, 10}));


    }
    public static void print(int[][] a){
        for(int i=0;i<a.length;i++){
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println();
    }

    public static int maxCoins(int[] iNums) {
int[] nums = new int[iNums.length + 2];
    int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
    nums[0] = nums[n++] = 1;


    int[][] dp = new int[n][n];
    print(dp);
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
        int right = left + k;
        for (int i = left + 1; i < right; ++i){
            System.out.println(String.format("left=%d,right=%d,i=%d", left,right,i));
            dp[left][right] = Math.max(dp[left][right],
                    nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            print(dp);
        }
    }

        return dp[0][n - 1];
}

    public static int myMaxCoinFun(int[] nums,int[][] mem,int start,int end){
       // System.out.println(String.format("%d,%d",start,end));
        if(start==end-1)
            return 0;
        if(start==end-2) {
            int coin=nums[start]*nums[start+1]*nums[end];
            mem[start][end]=coin;
            return coin;
        }
        if(mem[start][end]!=0){
            return mem[start][end];
        }
        int max=0;
        for(int i=start+1;i<end;i++){
            max=Math.max(max,nums[start]*nums[i]*nums[end]+myMaxCoinFun(nums,mem,start,i)+myMaxCoinFun(nums,mem,i,end));
        }
        mem[start][end]=max;
        return max;
    }
    public static int myMaxCoin(int[] iNums){
        int[] nums=new int[iNums.length+2];
        int n=1;
        for(int i=0;i<iNums.length;i++){
            if(iNums[i]>0)
                nums[n++]=iNums[i];
        }
        nums[0]=1;
        nums[n++]=1;
        int[][] mem=new int[n][n];
        return myMaxCoinFun(nums,mem,0,n-1);
    }


    ///revisit 1
    ///DP impl
    public static void maxCoins1(int[] nums){
        int len=nums.length;
        int[][] dp=new int[len][len];

        for(int g=0;g<len;g++){
            for(int i=0;i+g<len;i++){
                for(int j=0;j<=g;j++){
                    int l=1;
                    if(j!=0)
                        l=dp[i][i+j-1];
                }
            }
        }
    }

}
