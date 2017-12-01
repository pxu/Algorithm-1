package catagory.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhahua on 11/23/17.
 */
//http://www.lintcode.com/en/problem/permutations-ii/#
//http://www.jiuzhang.com/solution/permutations-ii/
public class Permutation2 {
    /*
 * @param :  A list of integers
 * @return: A list of unique permutations
 */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null) {
            return result;
        }
        boolean[] isUsed = new boolean[nums.length];
        Arrays.sort(nums);  // * don't forget
        helper(nums, new ArrayList<Integer>(), result, isUsed);
        return result;
    }
    private void helper(int[] nums, List<Integer> subset, List<List<Integer>> result, boolean[] isUsed) {
        if (subset.size() == nums.length) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isUsed[i]) {
                continue;
            }
            if (i != 0 && !isUsed[i - 1] && nums[i - 1] == nums[i]) { //永远保证 2' 2'' 2'''   取的是available的重复的数字的第一个
                continue;
            }
            subset.add(nums[i]);
            isUsed[i] = true;
            helper(nums, subset, result, isUsed);
            isUsed[i] = false;
            subset.remove(subset.size() - 1);
        }
    }
}

//class Solution {
//    /**
//     * @param nums: A list of integers.
//     * @return: A list of unique permutations.
//     */
//    public List<List<Integer>> permuteUnique(int[] nums) {
//
//        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
//
//        if (nums == null) {
//            return results;
//        }
//
//        if(nums.length == 0) {
//            results.add(new ArrayList<Integer>());
//            return results;
//        }
//
//        Arrays.sort(nums);
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        int[] visited = new int[nums.length];
//        for ( int i = 0; i < visited.length; i++){
//            visited[i] = 0;
//        }
//
//        helper(results, list, visited, nums);
//        return results;
//    }
//
//
//    public void helper(ArrayList<List<Integer>> results,
//                       ArrayList<Integer> list, int[] visited, int[] nums) {
//
//        if(list.size() == nums.length) {
//            results.add(new ArrayList<Integer>(list));
//            return;
//        }
//
//        for(int i = 0; i < nums.length; i++) {
//            if ( visited[i] == 1 || ( i != 0 && nums[i] == nums[i - 1]
//                    && visited[i-1] == 0)){
//                continue;
//            }
//            /*
//            上面的判断主要是为了去除重复元素影响。
//            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
//            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
//            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
//            不应该让后面的2使用。
//            */
//            visited[i] = 1;
//            list.add(nums[i]);
//            helper(results, list, visited, nums);
//            list.remove(list.size() - 1);
//            visited[i] = 0;
//        }
//    }
//}
