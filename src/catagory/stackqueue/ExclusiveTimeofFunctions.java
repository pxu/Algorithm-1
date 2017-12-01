package catagory.stackqueue;

import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/description/
 *
 */
public class ExclusiveTimeofFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastTime = 0;
        for (String log : logs) {
            String[] columns = log.split(":");
            if (columns[1].equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += Integer.parseInt(columns[2]) - lastTime;

                }
                stack.push(Integer.parseInt(columns[0]));
                lastTime = Integer.parseInt(columns[2]);
            } else {
                result[stack.peek()] += Integer.parseInt(columns[2]) + 1 - lastTime;
                stack.pop();
                lastTime = Integer.parseInt(columns[2]) + 1;
            }
        }
        return result;
    }
}