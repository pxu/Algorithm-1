package catagory.numbers;

import java.util.*;

/**
 * Created by zhahua on 11/3/17.
 */
public class FactorCombination {
    public static void main(String[] args) {
        factorCombHelper(2,32, new ArrayList<List<Integer>>(), new Stack<Integer>());
    }

//    private static void factorCombHelper(int start, int num, List<List<Integer>> result, Stack<Integer> stackqueue) {
//
//        if(num == 1) {
//            System.out.println(stackqueue);
//            result.add(new ArrayList(stackqueue));
//            return;
//        }
//        //int sqrt = (int)Math.sqrt(num);
//        for (int i = start; i <= num; i++) {
//            if(num % i == 0) {
//                stackqueue.push(i);
//                factorCombHelper(i, num / i, result, stackqueue);
//                stackqueue.pop();
//            }
//        }
//    }
    private static void factorCombHelper(int start, int num,   List<List<Integer>> result, Stack<Integer> stack) {
        int sqrt = (int)Math.sqrt(num);

        for(int i = start; i <= sqrt; i++ ) {
            if(num % i == 0) {
                stack.push(i);
                factorCombHelper(i, num / i, result, stack);

                stack.push(num / i);
                result.add(new ArrayList(stack));
                System.out.println(stack);
                stack.pop();
                stack.pop();
            }
        }
    }
}
