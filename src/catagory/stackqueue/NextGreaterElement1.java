package catagory.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
//https://leetcode.com/problems/next-greater-element-i/description/
public class NextGreaterElement1 {
       public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int num : nums2) {
            while(!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for(int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            result[i] = map.getOrDefault(num, -1);
        }
        return result;
    }
}