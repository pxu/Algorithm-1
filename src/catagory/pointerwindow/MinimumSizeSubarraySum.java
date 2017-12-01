package catagory.pointerwindow;

/**
 * Created by zhahua on 3/13/17.
 */
public class MinimumSizeSubarraySum {
  public static void main(String[] args){

  }
  public int minimumSize(int[] nums, int s) {
    // write your code here
    if(nums.length == 0){
      return -1;
    }
    if(s <= 0){
      return -1;
    }
    int sum = 0;
    int min = Integer.MAX_VALUE;
    int j = 0;
    for(int i = 0; i < nums.length; i++){
      while(j < nums.length && sum < s){
        sum += nums[j];
        j++;
      }
      if(sum >= s){
        min = Math.min(min,j - i);
      }
      sum -= nums[i];
    }
    if(min == Integer.MAX_VALUE){
      return -1;
    }
    return min;
  }
}
