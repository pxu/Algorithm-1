package catagory.dynamicprogramming;

/**
 * Created by zhahua on 10/10/17.
 */
public class maxSumOfThreeSubarrays {
  public static void main(String[] args) {
    new maxSumOfThreeSubarrays().maxSumOfThreeSubarrays(new int []{20,18,9,2,14,1,10,3,11,18},3);

  }
  private int sumNums(int[] nums, int start, int len) {
    int sum = 0;
    for(int i = start; i < start + len; i++) {
      sum += nums[i];
    }
    return sum;
  }
  private void print(int[] mem) {
    for(int i = 0; i < mem.length; i++) {
      System.out.print(mem[i] + " ");
    }
    System.out.println();
  }
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    if(nums.length < k * 3) {
      return null;
    }
    int[][] mem = new int[4][nums.length - k + 1];

    for(int i = 1; i <= 3; i++) {
      int start = (i - 1) * k;
      int rollingSum = sumNums(nums, start, k);
      for(int j = start; j <= nums.length - k; j++) {
        if(j != start) {
          rollingSum -= nums[j - 1];
          rollingSum += nums[j + k - 1];
        }
        if(i == 1 && j == 0) {
          mem[i][j] = rollingSum;
        }else if(i == 1) {
          mem[i][j] = Math.max(rollingSum, mem[i][j - 1]);
        }else if(j == start) {
          mem[i][j] = mem[i - 1][j - k] + rollingSum;
        }else {
          int oldValue = mem[i][j - 1];
          int newValue = mem[i - 1][j - k] + rollingSum;
          if(oldValue < newValue) {
            mem[i][j] = newValue;
          } else {

            mem[i][j] = oldValue;
          }

        }
      }
     // print(mem[i]);
    }
    int[] result = new int[3];
    int j = nums.length - k;
    for(int i = 3; i >= 1; i--) {
      for(; j >= (i - 1) * k + 1 && mem[i][j - 1] == mem[i][j]; j--) {
      }
      result[i - 1] = j;
      j -= k;
    }
    return result;
  }
}
