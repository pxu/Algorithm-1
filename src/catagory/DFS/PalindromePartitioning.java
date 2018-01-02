package catagory.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhahua on 11/23/17.
 * //https://leetcode.com/problems/palindrome-partitioning/description/
 */
public class PalindromePartitioning {
    /*
    * @param s: A string
    * @return: A list of lists of string
    */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if(s == null) {
            return result;
        }
        helper(s, 0, new ArrayList<String>(), result);
        return result;
    }
    public void helper(String s, int start, List<String> stack, List<List<String>> result) {
        if(start == s.length()) {
            result.add(new ArrayList(stack));
            return;
        }
        for(int i = start; i < s.length(); i++) {
            if(isPalindrome(s, start, i + 1)) {
                stack.add(s.substring(start, i + 1));
                helper(s, i + 1, stack, result);
                stack.remove(stack.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end) {
        for(int i = 0; i < (end - start + 1) / 2 ; i++ ) {
            if(s.charAt(start + i) != s.charAt(end - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
