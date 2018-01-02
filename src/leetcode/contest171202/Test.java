package leetcode.contest171202;

import java.util.Stack;

/**
 * Created by zhahua on 12/2/17.
 */
public class Test {
//1
    public int[] dailyTemperatures(int[] temperatures) {
      int[] result = new int[temperatures.length];
      if(temperatures == null || temperatures.length == 0) {
        return result;
      }
      Stack<Integer> stack = new Stack<>();

      for(int i = 0; i < temperatures.length; i++) {
        while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
          int day = stack.pop();
          result[day] = i - day;
        }
        stack.push(i);
      }
      return result;
    }

}
