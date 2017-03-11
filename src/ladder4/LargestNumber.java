package ladder4;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
/**
 * Created by zhahua on 2/25/17.
 */
public class LargestNumber {
    public static void main(String args[]){
        new Solution5().largestNumber(new int[]{1, 20, 23, 4, 8});
    }
}
class Solution5 {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        if(num.length==0)
            return "0";
        Integer[] arr=new Integer[num.length];
        for(int i=0;i<num.length;i++)
            arr[i]=num[i];

        Arrays.sort(arr,Collections.reverseOrder(new IntDictComparator()));
        if(arr[0].equals(0))
            return "0";
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<num.length;i++){
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
class IntDictComparator<Integer> implements Comparator<Integer>{
    @Override
    public int compare(Integer a,Integer b){
        String aStr=a.toString();
        String bStr=b.toString();
        if(aStr.length()==bStr.length()){
            return aStr.compareTo(bStr);
        }
        return (aStr+bStr).compareTo(bStr+aStr);

    }
}