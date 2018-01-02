package leetcode.contest171202;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by zhahua on 12/2/17.
 */
public class Test2 {
  public static void main(String[] args) {
    int[] values = new int[]{8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4};
    int value = new Test2().deleteAndEarn(values);
  }
  private void removeElement(TreeMap<Integer, Set<Integer>> map, int key, int value) {
    Set<Integer> set =  map.get(key);
    if(set != null) {
      set.remove(value);
      if(set.isEmpty()) {
        map.remove(key);
      }
    }
  }
  public int deleteAndEarn(int[] nums) {
    if(nums == null || nums.length == 0) {
      return 0;
    }
    Map<Integer,Integer> cnts = new HashMap<Integer, Integer>();

    for (int i = 0; i < nums.length; i++) {
      cnts.put(nums[i], cnts.getOrDefault(nums[i], 0) + 1);
    }

    TreeMap<Integer, Set<Integer>> values = new TreeMap<>();
    Map<Integer,Integer> valuesMap = new HashMap<Integer, Integer>();

    for(Integer num : cnts.keySet()) {
      int value = cnts.get(num) * num;
      value -= cnts.getOrDefault(num + 1, 0) * (num + 1);
      value -= cnts.getOrDefault(num - 1, 0) * (num - 1);
      values.computeIfAbsent(value, k -> new HashSet<Integer>()).add(num);
      valuesMap.put(num, value);
    }

    int earn = 0;

    while (!values.isEmpty()) {
      Map.Entry<Integer,Set<Integer>> curt = values.lastEntry();

      int curtNum = curt.getValue().iterator().next();
      earn += cnts.get(curtNum) * curtNum;
      removeElement(values, curt.getKey(), curtNum);
      Integer nextValue = valuesMap.get(curtNum + 1);
      valuesMap.remove(curtNum);
      if(nextValue != null) {
        removeElement(values,valuesMap.get(curtNum + 1), curtNum + 1);
        valuesMap.remove(curtNum + 1);
        cnts.remove(curtNum + 1);
        Integer nextNextValue = valuesMap.get(curtNum + 2);
        if(nextNextValue != null) {
          removeElement(values,valuesMap.get(curtNum + 2), curtNum + 2);
          int value = cnts.get(curtNum + 2) * (curtNum + 2);
          value -= cnts.getOrDefault(curtNum + 3, 0) * (curtNum + 3);
          values.computeIfAbsent(value, k -> new HashSet<Integer>()).add(curtNum + 2);
          valuesMap.put(curtNum + 2, value);
        }
      }
      Integer prevValue = valuesMap.get(curtNum - 1);
      if(prevValue != null) {
        removeElement(values,valuesMap.get(curtNum - 1), curtNum - 1);
        valuesMap.remove(curtNum - 1);
        Integer ppValue = valuesMap.get(curtNum - 2);
        if(ppValue != null) {
          removeElement(values,valuesMap.get(curtNum - 2), curtNum - 2);
          int value = cnts.get(curtNum - 2) * (curtNum - 2);
          value -= cnts.getOrDefault(curtNum - 3, 0) * (curtNum - 3);
          values.computeIfAbsent(value, k -> new HashSet<Integer>()).add(curtNum - 2);
          valuesMap.put(curtNum - 2, value);
        }
      }
    }

    return earn;
  }
}
