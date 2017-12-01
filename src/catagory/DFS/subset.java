package catagory.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhahua on 11/21/17.
 */
public class subset {
    /*
    * @param nums: A set of numbers
    * @return: A list of lists
    */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < (1 << (nums.length)); i++) {
            List<Integer> subset = new ArrayList<Integer>();
            for(int j = 0; j < nums.length; j++) {
                if((i & (1 << j)) > 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}
