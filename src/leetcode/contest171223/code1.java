package leetcode.contest171223;

public class code1 {
  public static void main(String[] args) {

  }
  public int dominantIndex(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return 0;
    }
    int[] top2 = new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
    int maxIndex = -1;

    for (int i = 0; i < nums.length; i++) {
      int j = 1;
      while (j >= 0 && nums[i] > top2[j]) {
          top2[j + 1] = top2[j];
          top2[j] = nums[i];
          if (j == 0) {
            maxIndex = i;
          }
          j--;
      }
    }
    if (top2[0] >= top2[1] * 2) {
      return maxIndex;
    }
    return -1;
  }
}
