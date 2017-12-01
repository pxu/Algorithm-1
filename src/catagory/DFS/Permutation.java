package catagory.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by zhahua on 11/19/17.
 */
//http://www.lintcode.com/en/problem/permutations/#
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) {
            return result;
        }
        helper(nums, new LinkedHashSet<Integer>(), result);
        return result;
    }
    private void helper(int[] nums, LinkedHashSet<Integer> used, List<List<Integer>> result) {
        if(used.size() == nums.length) {
            result.add(new ArrayList(used));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(used.contains(nums[i])) {
                continue;
            }
            used.add(nums[i]);
            helper(nums, used, result);
            used.remove(nums[i]);
        }
    }
}
