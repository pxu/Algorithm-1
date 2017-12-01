package leetcode.stackqueue;

import java.util.Stack;

/**
 * Created by zhahua on 11/25/17.
 */
//https://leetcode.com/problems/asteroid-collision/description/

public class AsteroidCollision {
    public static void main(String[] args) {

    }
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i != asteroids.length) {

            if (!stack.isEmpty() && stack.peek() > 0 && asteroids[i] < 0) {
                if(stack.peek() == -asteroids[i]) {
                    stack.pop();
                    i++;
                } else if(stack.peek() < -asteroids[i]) {
                    stack.pop();
                } else {
                    i++;
                }
            } else {
                stack.push(asteroids[i++]);
            }
        }
        int[] result = new int[stack.size()];
        int j = 0;
        for(Integer value : stack) {
            result[j++] = value;
        }
        return result;
    }
}
