package catagory.stackqueue;
//https://leetcode.com/problems/valid-parentheses/description/

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()) {
            switch (ch) {
                case '{' :
                    stack.push('}');
                    break;
                case '[' :
                    stack.push(']');
                    break;
                case '(' :
                    stack.push(')');
                    break;
                default:
                    if (stack.isEmpty() || stack.pop() != ch) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

}
