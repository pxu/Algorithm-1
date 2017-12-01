package catagory.stackqueue;

import java.util.Stack;

/**
 * Created by zhahua on 11/30/17.
 */
//https://leetcode.com/problems/baseball-game/description/
public class BasketBallGame {
        public int calPoints(String[] ops) {
            Stack<Integer> stack = new Stack<>();

            for (String op : ops) {
                switch (op) {
                    case "D":
                        stack.push(stack.peek() * 2); //*peek not peak
                        break;
                    case "C":
                        stack.pop();
                        break;
                    case "+":
                        int prev1 = stack.pop();
                        int prev2 = stack.pop();
                        stack.push(prev2);
                        stack.push(prev1);
                        stack.push(prev2 + prev1);
                        break;
                    default:
                        stack.push(Integer.parseInt(op));//parseInt
                        break;
                }
            }
            int sum = 0;
            for (Integer value : stack) {
                sum += value;
            }
            return sum;
        }

}
