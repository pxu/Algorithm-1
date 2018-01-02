package catagory.linkedlistnarray;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
 *
 */
public class FindLongestWord {
  public int removeDuplicates(int[] nums) {
    int repeat = 0;
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i - 1] == nums[i]) {
        repeat++;
      } else {
        repeat = 0;
      }
      nums[j] = nums[i];
      if (repeat < 2) {
        j++;
      }
    }
    return j;
  }
}
