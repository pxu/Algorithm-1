package catagory.stackqueue;

import java.util.Stack;

//****
//https://discuss.leetcode.com/topic/67881/single-pass-c-o-n-space-and-time-solution-8-lines-with-detailed-explanation
//https://leetcode.com/problems/132-pattern/description/
public class Find132Pattern {


    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();

        Integer maxS3 = Integer.MIN_VALUE;
        for(int i = nums.length - 2; i >= 0; i --) {
            while(!stack.isEmpty() && nums[i + 1] > stack.peek()) {
                int value = stack.pop(); // 会被pop出来的一定是有比他更大的数字
                maxS3 = Math.max(maxS3, value);
            }
            stack.push(nums[i + 1]);
            if(maxS3 > nums[i]) { //only need to compare maxS3, because we can guarantee there's a larger number
                return true;
            }
        }
        return false;
    }

}
