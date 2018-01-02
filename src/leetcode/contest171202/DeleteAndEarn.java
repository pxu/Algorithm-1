package leetcode.contest171202;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhahua on 12/2/17.
 */
//dp solution
// https://discuss.leetcode.com/topic/102381/delete-and-earn
// https://leetcode.com/contest/weekly-contest-61/problems/delete-and-earn/
public class DeleteAndEarn {
  public static void main(String[] args) {
    int[] values = new int[]{3,4,2};
    int value = new DeleteAndEarn().deleteAndEarn(values);
  }
  public int deleteAndEarn(int[] nums) {
    TreeMap<Integer, Integer> cnts = new TreeMap<>();
    for (int i = 0; i < nums.length; i++) {
      cnts.put(nums[i], cnts.getOrDefault(nums[i], 0) + 1);
    }
    int prevUse = 0;
    int prevNUse = 0;
    int prev = -1;
    for(Map.Entry<Integer, Integer> entry : cnts.entrySet()) {

      int currentUse;
      if (prev == entry.getKey() + 1) {
        currentUse = prevNUse + entry.getValue() * entry.getKey();
      } else {
        currentUse = Math.max(prevNUse + entry.getValue() * entry.getKey()
                      , prevUse + entry.getValue() * entry.getKey());
      }
      int currentNUse = Math.max(prevNUse, prevUse);
      prevNUse = currentNUse;
      prevUse = currentUse;
      prev = entry.getKey();
    }
    return Math.max(prevNUse, prevUse);
  }
}
