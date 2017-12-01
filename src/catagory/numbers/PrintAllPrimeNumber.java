package catagory.numbers;

//http://www.geeksforgeeks.org/print-all-prime-factors-of-a-given-number/

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhahua on 10/26/17.
 */
public class PrintAllPrimeNumber {
    public static void main(String[] args) {
        printAllPrimeNumber(98);
    }
    private static void printAllPrimeNumber(int num) {
        List<Integer> result = new ArrayList<>();

        int remain = num;
        while(remain % 2 == 0) {
            result.add(2);
            remain /= 2;
        }
        for(int i = 3; i <= Math.sqrt(remain); i += 2) {
            while(remain % i == 0) {
                result.add(i);
                remain /= i;
            }
        }
        if(remain > 2) {
            result.add(remain);
        }
        System.out.println(result.toString());
    }
}
