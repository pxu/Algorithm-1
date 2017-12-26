package catagory.hashnheap;

import java.util.HashSet;
import java.util.Set;

/**
 * http://www.lintcode.com/en/problem/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] num) {
    // write your code here
    Set<Integer> set = new HashSet<>();
    for (int n : num) {
      set.add(n);
    }
    int max = 0;
    for (Integer n : set) {
      if (!set.contains(n -1)) {
        int cnt = 1;
        for (int i = n + 1; set.contains(i); i++, cnt++) {
        }
        max = Math.max(cnt,max);
      }
    }
    return max;
  }
}
