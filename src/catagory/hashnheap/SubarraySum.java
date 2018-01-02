package catagory.hashnheap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/subarray-sum/
 * 把sum 存起来
 *
 */
public class SubarraySum {

  /**
   * @param nums: A list of integers
   * @return: A list of integers includes the index of the first number
   *          and the index of the last number
   */
  public ArrayList<Integer> subarraySum(int[] nums) {
    Map<Integer, Integer> sumIdx = new HashMap<>();

    int sum = 0;

    for (int i = 0; i < nums.length; i++) {
      sumIdx.put(sum, i);
      sum += nums[i];
      if (sumIdx.containsKey(sum)) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(sumIdx.get(sum));
        result.add(i);
        return result;
      }
    }
    return null;
  }
}
