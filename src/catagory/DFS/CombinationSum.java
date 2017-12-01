package catagory.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhahua on 11/13/17.
 */
public class CombinationSum {

    //https://leetcode.com/problems/combination-sum/description/

    public static void main(String[] args) {

    }
    class Solution {
        /*
      * @param candidates: A list of integers
      * @param target: An integer
      * @return: A list of lists of integers
      */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            // write your code here
            List<List<Integer>> result = new ArrayList<>();
            if (candidates == null || candidates.length == 0) {
                return result;
            }
            Arrays.sort(candidates);
            helper(candidates, 0, new ArrayList<Integer>(), target, result);
            return result;
        }

        //candidates is sorted
        private void helper(int[] candidates, int start, List<Integer> stack, int target, List<List<Integer>> result) {
            if (target == 0) {
                result.add(new ArrayList(stack));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    break;
                }
                stack.add(candidates[i]);
                helper(candidates, i, stack, target - candidates[i], result);
                stack.remove(stack.size() - 1);
            }

        }
    }
}
