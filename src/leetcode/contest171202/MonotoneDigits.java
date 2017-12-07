package leetcode.contest171202;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhahua on 12/2/17.
 */
public class MonotoneDigits {

  public static void main(String[] args) {
    new MonotoneDigits().monotoneIncreasingDigits(1234);
  }
  public int monotoneIncreasingDigits(int N) {
    int org = N;
    List<Integer> nums = new ArrayList<>();
    while(N != 0) {
      nums.add(N % 10);
      N /= 10;
    }
    int i;
    for(i = nums.size() - 2; i >= 0; i--) {
      if(nums.get(i + 1) > nums.get(i)) {
        break;
      }
    }
    if(i == -1) {
      return org;
    }
    int j;
    for(j = i + 1; j + 1 < nums.size() && nums.get(j).equals(nums.get(j + 1)); j++) {

    }
    nums.set(j, nums.get(j) - 1);
    for(int k = j - 1; k >=0; k--) {
      nums.set(k, 9);
    }
    int result = 0;
    for(int k = nums.size() - 1; k >=0; k--) {
      result *= 10;
      result += nums.get(k);
    }
    return result;
  }
}
