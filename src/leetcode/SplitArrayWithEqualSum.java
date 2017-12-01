package leetcode;


/**
 * Created by zhahua on 10/17/17.
 */

//https://leetcode.com/articles/split-array-with-equal-sum/
public class SplitArrayWithEqualSum {
    public static void main(String[] args) {
        new SplitArrayWithEqualSum().splitArray(new int[]{1,2,1,2,1,2,1});
    }
    public boolean splitArray(int[] array) {
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        if(sum % 4 != 0) {
            return false;
        }
        return splitArrayRec(array, 0, 0, sum / 3);
    }
    private boolean splitArrayRec(int[] array, int start, int depth, int target) {
        int sum = 0;
        if(depth == 3) {
            for(int i = start; i < array.length; i++) {
                sum += array[i];
            }
            return sum == target;
        }
        for(int i = start; i < array.length - (3 - depth); i++) {
            sum += array[i];
            if(sum == target) {
                boolean result = splitArrayRec(array, i + 1, depth + 1, target);
                if(result) {
                    return true;
                }
            }
        }
        return false;
    }
}
