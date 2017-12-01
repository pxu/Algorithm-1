package catagory.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhahua on 11/23/17.
 */
public class CombinationSum2 {
    public class Solution {
        /*
         * @param num: Given the candidate numbers
         * @param target: Given the target number
         * @return: All the combinations that sum to target
         */
        public List<List<Integer>> combinationSum2(int[] num, int target) {
            // write your code here
            List<List<Integer>> result = new ArrayList<>();
            if(num == null) {
                return result;
            }
            Arrays.sort(num);
            helper(num, 0, target, new ArrayList<Integer>(), result);
            return result;
        }
        public void helper(int[] num, int start, int target, List<Integer> stack, List<List<Integer>> result) {
            result.add(new ArrayList<Integer>(stack));

            for (int i = start; i < num.length; i++) {
                if(i != start && num[i] == num[i - 1]) {
                    continue;
                }
                if(num[i] > target) {
                    continue;
                }
                stack.add(num[i]);
                helper(num, i + 1, target - num[i], stack, result);
                stack.remove(stack.size() - 1);
            }
        }
    }
}
