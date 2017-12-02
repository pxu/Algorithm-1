package catagory.stackqueue;

import java.util.Stack;

//https://leetcode.com/problems/next-greater-element-ii/description/

public class NextGreaterElement2 {
        public int[] nextGreaterElements(int[] nums) {
            int[] result = new int[nums.length];
            for(int i = 0; i < result.length; i++) {
                result[i] = -1;
            }
            Stack<int[]> stack = new Stack<>();

            for (int i = 0; i < nums.length * 2; i++) {
                int j = i % nums.length;
                while (!stack.isEmpty() && stack.peek()[0] < nums[j]) {
                    int[] pair = stack.pop();
                    result[pair[1]] = nums[j];
                }
                stack.push(new int[]{nums[j], j});
            }
            return result;
        }

}
